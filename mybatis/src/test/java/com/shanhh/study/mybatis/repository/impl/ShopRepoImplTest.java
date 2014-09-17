package com.shanhh.study.mybatis.repository.impl;

import com.shanhh.study.mybatis.SpringContextTestParent;
import com.shanhh.study.mybatis.domain.Shop;
import com.shanhh.study.mybatis.repository.ShopRepo;
import org.apache.commons.lang3.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.Test;

import java.util.UUID;

import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertTrue;

public class ShopRepoImplTest extends SpringContextTestParent {

    @Autowired
    private ShopRepo shopRepo;

    @Test
    public void testSave() throws Exception {
        Shop shop = generateShop();
        assertEquals(1, shopRepo.save(shop));
    }

    @Test
    public void testListByCity() throws Exception {

        String city = UUID.randomUUID().toString();
        int times = RandomUtils.nextInt(10, 20);
        for (int i = 0; i < times; i++) {
            Shop shop = generateShop();
            shop.setCity(city);
            shopRepo.save(shop);
        }

        assertEquals(times, shopRepo.listByCity(city).size());
    }

    @Test
    public void testFind() throws Exception {

        Shop shop = generateShop();
        shopRepo.save(shop);

        Shop exist = shopRepo.find(shop.getShopId());
        assertEquals(shop.getCity(), exist.getCity());
        assertEquals(shop.getShopName(), exist.getShopName());

    }

}