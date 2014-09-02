package com.shanhh.study.spring.service;

import com.shanhh.study.spring.bean.User;
import org.apache.commons.lang3.RandomUtils;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * @author dan.shan
 * @since 2014-09-02 14:45
 */
@Service
public class UserServiceImpl implements UserService {

    @Override
    public User generateUser() {
        User user = new User();
        user.setUserId(RandomUtils.nextInt(1, 9999));
        user.setUsername(UUID.randomUUID().toString());

        return user;
    }

}
