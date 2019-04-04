package com.imooc.merchants.service;

import com.imooc.merchants.Vo.CreateMerchantsRequest;
import com.imooc.merchants.Vo.PassTemplate;
import com.imooc.merchants.Vo.Response;

/*

  对商户服务接口定义
 */
public interface IMerchantsServ {
    //创建商户服务
    Response createMerchants(CreateMerchantsRequest request);

    //根据商户id构建商户信息
    Response buildMerchantsInfoById(Integer id);

    //投放优惠券
    Response dropPassTemplate(PassTemplate template);
}
