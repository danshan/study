package com.shanhh.study.mybatis;

import com.shanhh.study.mybatis.domain.Shop;
import org.apache.commons.lang3.RandomUtils;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;

import java.util.UUID;

/**
 * @author dan.shan
 * @since 2014-09-17 16:07
 */
@ContextConfiguration(locations = {"classpath:/spring/applicationContext.xml"})
public abstract class SpringContextTestParent extends AbstractTestNGSpringContextTests {

    public Shop generateShop() {
        Shop shop = new Shop();
        shop.setShopName(UUID.randomUUID().toString());
        shop.setCity(UUID.randomUUID().toString());
        return shop;
    }

}
