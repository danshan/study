package com.shanhh.study.jedis.service;


import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;
import redis.clients.jedis.ShardedJedisPool;

import javax.annotation.Resource;

@ContextConfiguration(locations = {"classpath:/spring/applicationContext.xml"})
public class MonitorServiceImplTest extends AbstractTestNGSpringContextTests {

    @Resource
    private ShardedJedisPool jedisPool;

    @Resource
    private MonitorService monitorService;

    @Test
    public void testShardInfo() throws Exception {

        monitorService.shardInfo();

    }
}