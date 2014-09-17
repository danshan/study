package com.shanhh.study.mybatis.database;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.AfterReturningAdvice;
import org.springframework.aop.MethodBeforeAdvice;
import org.springframework.aop.ThrowsAdvice;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

/**
 * DataSource 切面
 *
 * @author dan.shan
 */
public class DataSourceAdvice implements MethodBeforeAdvice, AfterReturningAdvice, ThrowsAdvice {

    private static final Logger LOG = LoggerFactory.getLogger(DataSourceAdvice.class);

    /* (non-Javadoc)
     * @see org.springframework.aop.AfterReturningAdvice#afterReturning(java.lang.Object, java.lang.reflect.Method, java.lang.Object[], java.lang.Object)
     */
    @Override
    public void afterReturning(Object returnValue, Method method, Object[] args, Object target)
            throws Throwable {
    }

    /* (non-Javadoc)
     * @see org.springframework.aop.MethodBeforeAdvice#before(java.lang.reflect.Method, java.lang.Object[], java.lang.Object)
     */
    @Override
    public void before(Method method, Object[] args, Object target)
            throws Throwable {

        //首先获取到method所在类的注解
        Annotation[] objectAnnotations = method.getDeclaringClass().getAnnotations();
        DatasourceEnum datasource = DatasourceEnum.SHOP_SLAVE;
        // 获取类的默认数据源
        if (objectAnnotations != null && objectAnnotations.length > 0) {
            for (Annotation annotation : objectAnnotations) {
                if (Datasource.class.isInstance(annotation)) {
                    datasource = ((Datasource) annotation).dataSource();
                    break;
                }
            }
        }

        Annotation[] annotations = method.getAnnotations();
        String methodName = method.getName();
        String className = method.getDeclaringClass().getName();
        // 没有注解或者注解数组为空时, 使用slave库
        if (annotations == null || annotations.length == 0) {
            LOG.debug("AOP> use declaringClass annotation switch to {} datasource class: {}, method: {}",
                    datasource, className, methodName);
            DataSourceSwitcher.setDataSource(datasource);
            return;
        }

        for (Annotation annotation : annotations) {
            if (Datasource.class.isInstance(annotation)) {
                datasource = ((Datasource) annotation).dataSource();
                LOG.debug("AOP> switch to datasource: {}, class: {}, method: {}",
                        datasource, className, methodName);
                DataSourceSwitcher.setDataSource(datasource);
                return;
            }
        }

        LOG.debug("AOP> no annotation switch to {} datasource class: {}, method: {}",
                datasource, className, methodName);
        DataSourceSwitcher.setDataSource(DatasourceEnum.SHOP_SLAVE);

    }

    public void afterThrowing(Method method, Object[] args, Object target, Exception ex)
            throws Throwable {
        DataSourceSwitcher.clearDataSource();
        LOG.warn("switch datasource error, switch to new slave datasource class: {}, method: {}, ex: {}",
                target.getClass().getName(), method.getName(), ex.getMessage());
    }

}