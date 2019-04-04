package com.imooc.merchants.controller;

import com.alibaba.fastjson.JSON;
import com.imooc.merchants.Vo.CreateMerchantsRequest;
import com.imooc.merchants.Vo.PassTemplate;
import com.imooc.merchants.Vo.Response;
import com.imooc.merchants.service.IMerchantsServ;
import com.imooc.merchants.service.Impl.MerchantsServImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 商户服务controller
 */
@Slf4j
@RestController
@RequestMapping("/merchants")
public class MerchantsController {

    /**
     * 商户服务接口
     */

    private final MerchantsServImpl merchantsServ;

    @Autowired
    public MerchantsController(MerchantsServImpl merchantsServ) {
        this.merchantsServ = merchantsServ;
    }

    @ResponseBody
    @PostMapping("/create")
    public Response createMerchants(@RequestBody CreateMerchantsRequest request) {
        log.info("CreateMerchants: {}", JSON.toJSONString(request));
        return merchantsServ.createMerchants(request);
    }

    @ResponseBody
    @GetMapping("/{id}")
    public Response bulidMerchantsInfo(@PathVariable Integer id) {
        log.info("BulidMerchantsInfo:  {} ", id);
        return merchantsServ.buildMerchantsInfoById(id);
    }

    @PostMapping("/drop")
    @ResponseBody
    public Response dropPassTemplate(@RequestBody PassTemplate passTemplate) {
        log.info("DropPassTemplate:{}", passTemplate);
        return merchantsServ.dropPassTemplate(passTemplate);
    }
}
