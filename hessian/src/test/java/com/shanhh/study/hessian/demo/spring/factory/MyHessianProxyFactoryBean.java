package com.shanhh.study.hessian.demo.spring.factory;

import com.caucho.hessian.client.HessianProxyFactory;
import lombok.Getter;
import lombok.Setter;
import org.springframework.remoting.caucho.HessianProxyFactoryBean;

/**
 * 这个类帮助我们自定义超时时间等信息
 * @author dan.shan
 * @since 2014-09-18 19:04
 */
public class MyHessianProxyFactoryBean extends HessianProxyFactoryBean {

    private HessianProxyFactory proxyFactory = new HessianProxyFactory();

    @Setter @Getter
    private int readTimeOut = 10000;
    @Setter @Getter
    private int connectTimeOut = 10000;

    public void afterPropertiesSet() {
        proxyFactory.setReadTimeout(readTimeOut);
        proxyFactory.setConnectTimeout(connectTimeOut);
        setProxyFactory(proxyFactory);
        super.afterPropertiesSet();
    }
}
