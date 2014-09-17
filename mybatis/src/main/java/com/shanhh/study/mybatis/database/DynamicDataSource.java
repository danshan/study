package com.shanhh.study.mybatis.database;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * 数据源动态切换
 * @author dan.shan
 *
 */
public class DynamicDataSource extends AbstractRoutingDataSource {

    /* (non-Javadoc)
     * @see org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource#determineCurrentLookupKey()
     */
    @Override
    protected Object determineCurrentLookupKey() {
        Object obj = DataSourceSwitcher.getDataSource();
        return obj;
    }

}
