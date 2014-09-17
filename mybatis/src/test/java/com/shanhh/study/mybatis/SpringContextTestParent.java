package com.shanhh.study.mybatis;

import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;

/**
 * @author dan.shan
 * @since 2014-09-17 16:07
 */
@ContextConfiguration(locations = {"classpath:/spring/applicationContext.xml"})
public abstract class SpringContextTestParent extends AbstractTestNGSpringContextTests {}
