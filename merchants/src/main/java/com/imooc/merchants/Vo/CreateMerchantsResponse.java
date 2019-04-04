package com.imooc.merchants.Vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/*
    创建商户响应对象
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateMerchantsResponse {
    //商户id：创建失败则为-1
    private Integer id;
}
