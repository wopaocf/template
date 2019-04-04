package com.imooc.passbook.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * <h1>用户领取优惠券的请求对象</h1>
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GainPassTemplateRequest {
     /**用户 id**/
    private Long userId;

    /**PassTemplate对象**/
    private PassTemplate passTemplate;


}
