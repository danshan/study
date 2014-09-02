package com.shanhh.study.spring.loadconfig;

import com.shanhh.study.spring.service.UserService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Spring中加载xml配置文件的六种方式: ClassPathXmlApplicationContext  编译路径
 * @author dan.shan
 * @since 2014-09-02 15:36
 */
public class ByClassPathXmlApplicationContext {

    public static void main(String[] args) {

        ApplicationContext factory = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
        // src目录下的
        // ApplicationContext factory = new ClassPathXmlApplicationContext("appcontext.xml");
        // ApplicationContext factory = new ClassPathXmlApplicationContext(new String[] {"bean1.xml","bean2.xml"});
        // src/conf 目录下的
        // ApplicationContext factory = new ClassPathXmlApplicationContext("conf/appcontext.xml");
        // ApplicationContext factory = new ClassPathXmlApplicationContext("file:G:/Test/src/appcontext.xml");

        // get bean by name
        UserService userService = (UserService) factory.getBean("userServiceImpl");
        System.out.println(userService.generateUser());

        // get bean by class
        userService = factory.getBean(UserService.class);
        System.out.println(userService.generateUser());
    }

}
