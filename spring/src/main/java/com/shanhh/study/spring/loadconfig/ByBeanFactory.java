package com.shanhh.study.spring.loadconfig;

import com.shanhh.study.spring.service.UserService;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.core.io.ClassPathResource;

/**
 * @author dan.shan
 * @since 2014-09-02 15:49
 */
public class ByBeanFactory {

    public static void main(String[] args) {
        BeanDefinitionRegistry reg = new DefaultListableBeanFactory();
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(reg);
        reader.loadBeanDefinitions(new ClassPathResource("applicationContext.xml"));
        BeanFactory factory = (BeanFactory)reg;

        // get bean by name
        UserService userService = (UserService)factory.getBean("userServiceImpl");
        System.out.println(userService.generateUser());

        // get bean by class
        userService = factory.getBean(UserService.class);
        System.out.println(userService.generateUser());
    }

}
