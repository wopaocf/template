package com.imooc.passbook.service.Impl;

import com.imooc.passbook.constant.Constants;
import com.imooc.passbook.dao.MerchantsDao;
import com.imooc.passbook.entity.Merchants;
import com.imooc.passbook.mapper.PassTemplateRowMapper;
import com.imooc.passbook.service.IInventoryService;
import com.imooc.passbook.service.IUserPassService;
import com.imooc.passbook.utils.RowKeyGenUtil;
import com.imooc.passbook.vo.*;
import com.spring4all.spring.boot.starter.hbase.api.HbaseTemplate;
import lombok.extern.slf4j.Slf4j;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.filter.CompareFilter;
import org.apache.hadoop.hbase.filter.FilterList;
import org.apache.hadoop.hbase.filter.LongComparator;
import org.apache.hadoop.hbase.filter.SingleColumnValueFilter;
import org.apache.hadoop.hbase.util.Bytes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

/**
 *
 * <h1>获取库存信息，只返回用户没有领取的</h1>
 */
/**优惠券过多,采用分页或者缓存的方法**/
@Slf4j
@Service
public class InventoryServiceImpl implements IInventoryService {
    /**HBase客户端**/
    private final HbaseTemplate hbaseTemplate;
    /**MerchantsDao*/
    private final MerchantsDao merchantsDao;
    /** */
    private final IUserPassService userPassService;
    @Autowired
    public InventoryServiceImpl(HbaseTemplate hbaseTemplate, MerchantsDao merchantsDao, IUserPassService userPassService) {
        this.hbaseTemplate = hbaseTemplate;
        this.merchantsDao = merchantsDao;
        this.userPassService = userPassService;
    }

    @Override
    public Response getInventoryInfo(Long userId) throws Exception {

          Response allUserPass=userPassService.getUserAllPassInfo(userId);
          List<PassInfo> passInfos=(List<PassInfo>) allUserPass.getData();
          List<PassTemplate> excludeObject=passInfos.stream().map(
                           PassInfo::getPassTemplate
          ).collect(Collectors.toList());

          List<String> execludeIds=new ArrayList<>();
          excludeObject.forEach(m -> execludeIds.add(
                  RowKeyGenUtil.genPassTemplateRowKey(m)
          ));


        return new Response(new InventoryResponse(userId,
                buildPassTemplateInfo(getAvailablePassTemplate(execludeIds))));
    }

    /**
     * <h2>获取系统中可用的优惠券</h2>
     * @param excludeIds  需要排除的优惠券 ids
     * @return   {@link PassTemplate}
     */
    private List<PassTemplate> getAvailablePassTemplate(List<String> excludeIds){

        FilterList filterList=new FilterList(FilterList.Operator.MUST_PASS_ONE);

        filterList.addFilter(new SingleColumnValueFilter(
                        Bytes.toBytes(Constants.PassTemplateTable.FAMILY_C),
                        Bytes.toBytes(Constants.PassTemplateTable.LIMIT),
                        CompareFilter.CompareOp.GREATER,
                        new LongComparator(0L)
                        )
                );
        filterList.addFilter(new SingleColumnValueFilter(
                        Bytes.toBytes(Constants.PassTemplateTable.FAMILY_C),
                        Bytes.toBytes(Constants.PassTemplateTable.LIMIT),
                        CompareFilter.CompareOp.EQUAL,
                        Bytes.toBytes("-1")
                )
        );
        Scan  scan=new Scan();
        scan.setFilter(filterList);

        List<PassTemplate> validTemplates=hbaseTemplate.find(
                       Constants.PassTemplateTable.TABLE_NAME,scan,new PassTemplateRowMapper());
        List<PassTemplate>  availablePassTemplate=new ArrayList<>();

        Date cur=new Date();

       for (PassTemplate validTemplate:validTemplates){
           /**过滤使用的id*/
           if (excludeIds.contains(RowKeyGenUtil.genPassTemplateRowKey(validTemplate)))
               continue;

           /**判断消费日期*/
           if(cur.getTime()>=validTemplate.getStart().getTime()
              &&cur.getTime()<=validTemplate.getEnd().getTime())
              availablePassTemplate.add(validTemplate);
       }
        return  availablePassTemplate;
    }

    /**
     * <h2>构造优惠券信息</h2>
     * @param passTemplates  {@link PassTemplate}
     * @return {@link PassTemplateInfo}
     */
    private  List<PassTemplateInfo> buildPassTemplateInfo(List<PassTemplate> passTemplates){

        Map<Integer, Merchants> merchantsMap=new HashMap<>();

        List<Integer> merchantsIds=passTemplates.stream().map(
                PassTemplate::getId
        ).collect(Collectors.toList());

        List<Merchants> merchants=merchantsDao.findByIdIn(merchantsIds);

        merchants.forEach(m ->
                merchantsMap.put(m.getId(),m));

        List<PassTemplateInfo> result=new ArrayList<>(passTemplates.size());

      for(PassTemplate passTemplate:passTemplates){
          Merchants mc=merchantsMap.getOrDefault(passTemplate.getId(),
                  null);
          if (null==mc){
              log.error("Merchants Error:{}",passTemplate.getId());
              continue;
          }
          result.add(new PassTemplateInfo(passTemplate,mc));
      }
         return result;
    }
}
