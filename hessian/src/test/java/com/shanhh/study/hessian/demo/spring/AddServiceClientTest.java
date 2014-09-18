package com.shanhh.study.hessian.demo.spring;

import com.caucho.hessian.client.HessianProxyFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.testng.annotations.Test;

import java.net.MalformedURLException;

import static org.testng.AssertJUnit.assertEquals;

/**
 * @author dan.shan
 * @since 2014-09-18 16:55
 */
public class AddServiceClientTest {

    @Test
    public void testAdd_WithoutSpring() throws MalformedURLException {
        String url = "http://localhost:8080/api/service/addService";
        HessianProxyFactory factory = new HessianProxyFactory();
        AddService service = (AddService) factory.create(AddService.class, url);
        assertEquals(3, service.add(1, 2));
    }

    @Test
    public void testAdd_WithSpring() {

        ApplicationContext context = new ClassPathXmlApplicationContext("/spring/applicationContext-hessian-client.xml");
        AddService service = (AddService) context.getBean("addService");
        assertEquals(3, service.add(1, 2));

    }

}
