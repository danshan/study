package com.shanhh.study.spring.loadconfig;

import com.shanhh.study.spring.service.UserService;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

/**
 * Spring中加载xml配置文件的六种方式: XmlBeanFactory 引用资源
 * @author dan.shan
 * @since 2014-09-02 14:44
 */
public class ByXmlBeanFactory {

    public static void main(String[] args) {
        Resource resource = new ClassPathResource("applicationContext.xml");
        BeanFactory factory = new XmlBeanFactory(resource);

        // get bean by name
        UserService userService = (UserService)factory.getBean("userServiceImpl");
        System.out.println(userService.generateUser());

        // get bean by class
        userService = factory.getBean(UserService.class);
        System.out.println(userService.generateUser());
    }

}
