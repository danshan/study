package com.shanhh.study.mybatis.repository.impl;

import com.shanhh.study.mybatis.dao.po.ShopPO;
import com.shanhh.study.mybatis.domain.Shop;
import com.shanhh.study.mybatis.dao.ShopDao;
import com.shanhh.study.mybatis.repository.ShopRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.LinkedList;
import java.util.List;

/**
 * @author dan.shan
 * @since 2014-09-17 14:40
 */
@Repository
public class ShopRepoImpl implements ShopRepo {

    @Autowired
    private ShopDao shopDao;

    @Override
    public int save(Shop shop) {
        ShopPO po = new ShopPO(shop);
        int result = shopDao.save(po);

        shop.setShopId(po.getShopId());
        return result;
    }

    @Override
    public List<Shop> listByCity(String city) {
        List<ShopPO> shopPOList = shopDao.listByCity(city);
        List<Shop> shopList = new LinkedList<Shop>();

        for (ShopPO po : shopPOList) {
            shopList.add(po.toDomain());
        }
        return shopList;
    }

    @Override
    public Shop find(int shopId) {
        ShopPO po = shopDao.find(shopId);
        if (po == null) {
            return null;
        } else {
            return po.toDomain();
        }
    }
}
