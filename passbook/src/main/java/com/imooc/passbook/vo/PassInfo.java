package com.imooc.passbook.vo;

import com.imooc.passbook.entity.Merchants;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * <h1>用户领取优惠券信息</h1>
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PassInfo {
    /**优惠券*/
    private Pass pass;
    /**优惠券模板*/
    private PassTemplate passTemplate;
    /**商户信息**/
    private Merchants merchants;

}
