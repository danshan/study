package com.shanhh.study.jedis.service.impl;

import com.alibaba.fastjson.JSON;
import com.shanhh.study.jedis.bean.User;
import com.shanhh.study.jedis.service.SingleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * @author dan.shan
 * @since 2014-09-03 15:08
 */
@Service
public class SingleServiceImpl implements SingleService {

    @Autowired
    private JedisPool jedisPool;

    @Override
    public void saveUser(User user) {
        String value = JSON.toJSONString(user);

        Jedis jedis = jedisPool.getResource();
        jedis.set(getRedisKey(user.getUserId()), value);
        jedisPool.returnResource(jedis);
    }

    @Override
    public User findUser(int userId) {
        Jedis jedis = jedisPool.getResource();
        String value = jedis.get(getRedisKey(userId));
        jedisPool.returnResource(jedis);

        return JSON.parseObject(value, User.class);
    }

    private String getRedisKey(int userId) {
        return "userId:" + userId;
    }
}
