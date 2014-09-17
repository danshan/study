package com.shanhh.study.mybatis.database;

/**
 * 数据源选择器, 实现读写分离
 * @author dan.shan
 *
 */
public class DataSourceSwitcher {

    private static final ThreadLocal<String> contextHolder = new ThreadLocal<String>();

    private DataSourceSwitcher() {}

    public static void setDataSource(DatasourceEnum dataSource) {
        contextHolder.set(dataSource.getDatasource());
    }
    
    public static String getDataSource() {
        return contextHolder.get();
    }
    
    public static void clearDataSource() {
        contextHolder.remove();
    }

}
