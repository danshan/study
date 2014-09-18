package com.shanhh.study.hessian.demo.servlet;

import com.caucho.hessian.client.HessianProxyFactory;

import java.net.MalformedURLException;

/**
 * @author dan.shan
 * @since 2014-09-18 13:54
 */
public class ServletServiceClient {

    static final String url = "http://localhost:8080/helloworld";

    public static void main(String[] args) throws MalformedURLException {
        HessianProxyFactory factory = new HessianProxyFactory();
        ServletService servletService = (ServletService) factory.create(ServletService.class, url);
        System.out.println("helloworld(): " + servletService.helloworld());
    }

}
