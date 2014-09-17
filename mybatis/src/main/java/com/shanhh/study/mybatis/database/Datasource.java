package com.shanhh.study.mybatis.database;

import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * 标识是否使用主库 自定义注解<br/>
 * 默认使用类注解(没有注解, 默认slave)<br/>
 * 如果方法注解为slave, 则覆盖类注解, 使用从库<br/>
 * 如果注解名称为master, 则覆盖类注解, 使用主库
 * @author sam.yang
 * @since Jul 24, 2013 5:41:31 PM
 */
@Target({java.lang.annotation.ElementType.METHOD, java.lang.annotation.ElementType.TYPE})
@Retention(java.lang.annotation.RetentionPolicy.RUNTIME)
@Inherited
public @interface Datasource {
    DatasourceEnum dataSource() default DatasourceEnum.SHOP_MASTER;
}
