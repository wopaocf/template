package com.imooc.passbook.utils;

import com.imooc.passbook.vo.Feedback;
import com.imooc.passbook.vo.GainPassTemplateRequest;
import com.imooc.passbook.vo.PassTemplate;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;

/**
 * <h1>RowKey 生成器工具类</h1>
 */
@Slf4j
public class RowKeyGenUtil {
    /**
     * <h2>根据提供的 PassTemplate 对象生成 Rowkey</h2>
     * @param passTemplate  {@link PassTemplate}
     * @return String RowKey
     */

    public  static String genPassTemplateRowKey(PassTemplate passTemplate){

               String passInfo=String.valueOf(passTemplate.getId())+"_"+passTemplate.getTitle();
               String rowkey= DigestUtils.md5Hex(passInfo);//为了使数据分散，分散到多个节点
               log.info("GenPassTemplateRowKey:{},{}",passInfo,rowkey);

               return rowkey;

    }

    /**
     * <h2>根据提供的领取优惠券请求生成RowKey,只可以在领取的时候使用</h2>
     * Pass RowKey=reversed(userId)+inverse(timestap)+PassTemplate RowKey   inverse Long.MaxValue-当前的时间戳
     * @param request {@link GainPassTemplateRequest}
     * @return   String RowKey
     */
    public static String genPassRowKey(GainPassTemplateRequest request){

            return  new StringBuilder(String.valueOf(request.getUserId())).reverse().toString()
                    +(Long.MAX_VALUE-System.currentTimeMillis())
                    +genPassTemplateRowKey(request.getPassTemplate());

    }



    /**
     * <h2>根据Feedback 构造RowKey</h2>
     * @param feedback  {@link Feedback}
     * @return String RowKey
     */
    public static String  genFeedbackRowKey(Feedback feedback){


        return  new StringBuilder(String.valueOf(feedback.getUserId())).reverse().toString()+
                (Long.MAX_VALUE-System.currentTimeMillis());

    }
}
