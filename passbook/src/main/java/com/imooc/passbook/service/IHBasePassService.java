package com.imooc.passbook.service;

import com.imooc.passbook.vo.PassTemplate;

/**
 * <h1>Pass HBase 服务</h1>
 */
public interface IHBasePassService {
    /**
     * <h2>将PassTemplate 写入HBase</h2>
     * @param passTemplate  {@link  PassTemplate}
     * @return  true/false
     */
    boolean dropPassTemplateToHBase(PassTemplate passTemplate);
}
