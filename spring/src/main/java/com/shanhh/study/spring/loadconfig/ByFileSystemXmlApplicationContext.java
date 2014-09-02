package com.shanhh.study.spring.loadconfig;

import com.shanhh.study.spring.service.UserService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

/**
 * @author dan.shan
 * @since 2014-09-02 15:45
 */
public class ByFileSystemXmlApplicationContext {

    public static void main(String[] args) {
        ApplicationContext factory = new FileSystemXmlApplicationContext("applicationContext.xml");
        //使用了  classpath:  前缀,作为标志,  这样,FileSystemXmlApplicationContext 也能够读入classpath下的相对路径
        // ApplicationContext factory = new FileSystemXmlApplicationContext("classpath:appcontext.xml");
        // ApplicationContext factory = new FileSystemXmlApplicationContext("file:G:/Test/src/appcontext.xml");
        // ApplicationContext factory = new FileSystemXmlApplicationContext("G:/Test/src/appcontext.xml");

        // get bean by name
        UserService userService = (UserService) factory.getBean("userServiceImpl");
        System.out.println(userService.generateUser());

        userService = factory.getBean(UserService.class);
        System.out.println(userService.generateUser());
    }

}
