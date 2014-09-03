package com.shanhh.study.jedis.service.impl;

import com.alibaba.fastjson.JSON;
import com.shanhh.study.jedis.bean.User;
import com.shanhh.study.jedis.service.ShardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;

/**
 * @author dan.shan
 * @since 2014-09-02 19:40
 */
@Service
public class ShardServiceImpl implements ShardService {

    @Autowired
    private ShardedJedisPool jedisPool;

    @Override
    public void saveUser(User user) {
        String value = JSON.toJSONString(user);

        ShardedJedis jedis = jedisPool.getResource();
        jedis.set(getRedisKey(user.getUserId()), value);
        jedisPool.returnResource(jedis);
    }

    @Override
    public User findUser(int userId) {
        ShardedJedis jedis = jedisPool.getResource();
        String value = jedis.get(getRedisKey(userId));
        jedisPool.returnResource(jedis);

        return JSON.parseObject(value, User.class);
    }

    private String getRedisKey(int userId) {
        return "userId:" + userId;
    }
}
