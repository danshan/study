package com.shanhh.study.mybatis.repository;

import com.shanhh.study.mybatis.database.Datasource;
import com.shanhh.study.mybatis.database.DatasourceEnum;
import com.shanhh.study.mybatis.domain.Shop;

import java.util.List;

/**
 * @author dan.shan
 * @since 2014-09-17 14:35
 */
public interface ShopRepo {

    @Datasource(dataSource = DatasourceEnum.SHOP_MASTER)
    int save(Shop user);

    List<Shop> listByCity(String city);

    Shop find(int shopId);

}
