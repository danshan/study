package com.shanhh.study.jedis.service.impl;

import com.shanhh.study.jedis.bean.User;
import com.shanhh.study.jedis.service.ShardService;
import org.apache.commons.lang3.RandomUtils;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

import javax.annotation.Resource;
import java.util.UUID;

import static org.testng.AssertJUnit.assertEquals;

@ContextConfiguration(locations = {"classpath:/spring/applicationContext.xml"})
public class ShardServiceImplTest extends AbstractTestNGSpringContextTests {

    @Resource
    private ShardService shardService;

    @Test
    public void testSaveUser() {

        User user = new User();
        user.setUserId(RandomUtils.nextInt(1, 10000));
        user.setUsername(UUID.randomUUID().toString());

        shardService.saveUser(user);

        User saved = shardService.findUser(user.getUserId());
        assertEquals(user.getUserId(), saved.getUserId());
        assertEquals(user.getUsername(), saved.getUsername());

    }

}