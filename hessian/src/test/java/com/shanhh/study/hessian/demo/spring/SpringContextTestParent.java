package com.shanhh.study.hessian.demo.spring;

import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;


/**
 * @author dan.shan
 * @since 2014-09-17 16:07
 */
@ContextConfiguration(locations = {"classpath:/spring/applicationContext-hessian-client.xml"})
public abstract class SpringContextTestParent extends AbstractTestNGSpringContextTests {

}