package com.imooc.passbook.dao;

import com.imooc.passbook.entity.Merchants;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * MerchantsDao 接口
 *
 */
public interface MerchantsDao  extends JpaRepository<Merchants,Integer>{
    /**
     * 通过id 获取用户对象
     * @param id 商户 id
     * @return {@link Merchants}
     */
    Merchants findByAndId(Integer id);

    /**
     * <h2>根据商户名称获取商户对象</h2>
      * @param name
     * @return {@link Merchants}
     */
    Merchants  findByName(String name);

    /**
     * <h2>根据商户ids获取商户对象</h2>
     * @param ids
     * @return  {@link Merchants}
     */
    List<Merchants>  findByIdIn(List<Integer> ids);
}
