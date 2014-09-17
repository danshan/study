package com.shanhh.study.mybatis.dao;

import com.shanhh.study.mybatis.dao.po.ShopPO;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author dan.shan
 * @since 2014-09-17 14:42
 */
@Repository
public interface ShopDao {

    int save(ShopPO shopPO);

    List<ShopPO> listByCity(String city);

    ShopPO find(int shopId);
}
