package com.shanhh.study.mybatis.database;

/**
 * @author dan.shan
 * @since 2013-12-18 10:39 AM
 */
public enum DatasourceEnum {

    SHOP_MASTER("shop_master"),
    SHOP_SLAVE("shop_slave"),

    PIC_MASTER("pic_master"),
    PIC_SLAVE("pic_slave");

    private final String datasource;

    private DatasourceEnum(String datasource) {
        this.datasource = datasource;
    }

    public String getDatasource() {
        return datasource;
    }
}
