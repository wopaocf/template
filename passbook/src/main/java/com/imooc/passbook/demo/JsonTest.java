package com.imooc.passbook.demo;

import com.alibaba.fastjson.JSON;

public class JsonTest {
    public static void main(String[] args) {
        /**
         * json字符串转化为对象
         */
        String jsonString="{name:'Antony',age:'12',sex:'male',telephone:'15756226878'}";
        Staff staff= JSON.parseObject(jsonString,Staff.class);
        System.out.println(staff.toString());


        /**
         * 对象转化为json字符串
         */
        String jsonStr=JSON.toJSONString(staff);
        System.out.println(jsonStr);

    }
}
