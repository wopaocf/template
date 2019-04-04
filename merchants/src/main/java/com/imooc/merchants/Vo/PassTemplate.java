package com.imooc.merchants.Vo;

import com.imooc.merchants.Dao.MerchantsDao;
import com.imooc.merchants.constant.ErrorCode;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/*
  投放的优惠券对象定义
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PassTemplate {
    //所属商户 id
    private Integer id;
    private String title;
    //优惠券标题
    private String summary;
    //优惠券的详细信息
    private String desc;
    //最大个数限制
    private Long limit;

    //优惠券是否有token,用于商户核销,token存储在Redis set中，每次从Redis中获取
    private Boolean hasToken;
    //优惠券背景色
    private Integer background;
    //优惠券开始时间
    private Date start;
    private Date end;

    //优惠券结束时间
    //校验优惠券对象的有效性
    public ErrorCode validate(MerchantsDao merchantsDao) {
        if (null == merchantsDao.findByAndId(id)) {
            return ErrorCode.MERCHANTS_NOT_EXIST;
        }
        return ErrorCode.SUCCESS;
    }
}
