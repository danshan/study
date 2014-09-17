package com.shanhh.study.mybatis.database;

import com.shanhh.study.mybatis.domain.Shop;
import com.shanhh.study.mybatis.repository.ShopRepo;
import org.testng.annotations.Test;

import java.lang.reflect.Method;
import java.sql.SQLException;

import static org.testng.AssertJUnit.assertEquals;

public class DataSourceAdviceTest {

    DataSourceAdvice advice = new DataSourceAdvice();

    @Test
    public void testBefore_AnnotationForMethod() throws Throwable {
        Method save = ShopRepo.class.getDeclaredMethod("save", Shop.class);
        advice.before(save, null, null);
        assertEquals(DatasourceEnum.SHOP_MASTER.getDatasource(), DataSourceSwitcher.getDataSource());
    }

    @Test
    public void testBefore_AnnotationForClass() throws Throwable {
        Method find = ShopRepo.class.getDeclaredMethod("find", int.class);
        advice.before(find, null, null);
        assertEquals(DatasourceEnum.SHOP_SLAVE.getDatasource(), DataSourceSwitcher.getDataSource());
    }

    @Test
    public void testBefore_AnnotationNothing() throws Throwable {
        Method listByCity = ShopRepo.class.getDeclaredMethod("listByCity", String.class);
        advice.before(listByCity, null, null);
        assertEquals(DatasourceEnum.SHOP_SLAVE.getDatasource(), DataSourceSwitcher.getDataSource());
    }

    @Test
    public void testAfterThrowing() throws Throwable {
        Method listByCity = ShopRepo.class.getDeclaredMethod("listByCity", String.class);
        advice.afterThrowing(listByCity, null, ShopRepo.class, new SQLException("fuck gfw"));
        assertEquals(null, DataSourceSwitcher.getDataSource());
    }
}