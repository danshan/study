package com.shanhh.study.jedis.service;

import com.shanhh.study.jedis.bean.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.ShardedJedisPool;

/**
 * @author dan.shan
 * @since 2014-09-02 19:40
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private ShardedJedisPool jedisPool;

    @Override
    public void saveUser(User user) {
    }

    @Override
    public User findUser(int userId) {
        return null;
    }
}
