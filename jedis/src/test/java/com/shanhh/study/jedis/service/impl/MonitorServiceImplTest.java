package com.shanhh.study.jedis.service.impl;


import com.shanhh.study.jedis.service.MonitorService;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

import javax.annotation.Resource;

@ContextConfiguration(locations = {"classpath:/spring/applicationContext.xml"})
public class MonitorServiceImplTest extends AbstractTestNGSpringContextTests {

    @Resource
    private MonitorService monitorService;

    @Test
    public void testShardInfo() throws Exception {

        monitorService.shardInfo();

    }
}