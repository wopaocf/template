package com.imooc.merchants.service;

import com.alibaba.fastjson.JSON;
import com.imooc.merchants.Vo.CreateMerchantsRequest;;
import com.imooc.merchants.service.Impl.MerchantsServImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * 商户服务测试类
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class MerchantsServiceTest {

    @Autowired
    private IMerchantsServ merchantsServ;

    @Test
    public void testCreateMerchantServ() {

        CreateMerchantsRequest request = new CreateMerchantsRequest();
        request.setName("muke");
        request.setLogoUrl("www.imooc.com");
        request.setBusinessLicenseUrl("www.imooc.com");
        request.setPhone("15756226878");
        request.setAddress("beijing");

     System.out.println(JSON.toJSONString(merchantsServ.createMerchants(request)));

    }
//    @Test
//    public  void testBuildMerchantsInfoById(){
//        System.out.println(JSON.toJSONString(merchantsServ.buildMerchantsInfoById(9)));
//    }
//
//    public void testDropPassTemplate(){
//        PassTemplate passTemplate=new PassTemplate();
//        passTemplate.setId(9);
//        passTemplate.setTitle("title：慕课");
//        passTemplate.setSummary("简介:慕课");
//        passTemplate.setDesc("详情：慕课");
//        passTemplate.setLimit(1000L);
//        passTemplate.setHasToken(false);
//        passTemplate.setBackground(3);
//        passTemplate.setStart(new Date());
//        passTemplate.setEnd(DateUtils.addDays(new Date(),10));
//        System.out.println(JSON.toJSONString(merchantsServ.dropPassTemplate(passTemplate)));
//    }
}
