package com.shanhh.study.spring.loadconfig;

import com.shanhh.study.spring.service.UserService;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author dan.shan
 * @since 2014-09-02 16:01
 */
public class ByXmlWebApplicationContext extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        ServletContext servletContext = request.getSession().getServletContext();
        ApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(servletContext);

        // get bean by name
        UserService userService = (UserService)ctx.getBean("userServiceImpl");
        System.out.println(userService.generateUser());

        // get bean by class
        userService = ctx.getBean(UserService.class);
        System.out.println(userService.generateUser());
    }
}
