package com.imooc.merchants.Dao;


import com.imooc.merchants.entity.Merchants;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Dao接口
 */
@Repository
public interface MerchantsDao extends JpaRepository<Merchants, Integer> {



    //根据id获取商户对象
    Merchants findByAndId(Integer id);

    //根据商户名称获取商户对象
    /**
     * @return {@link Merchants}
     */

    Merchants findByName(String name);

}
