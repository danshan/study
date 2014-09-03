package com.shanhh.study.jedis.service;

import com.shanhh.study.jedis.bean.User;

/**
 * @author dan.shan
 * @since 2014-09-02 19:38
 */
public interface UserService {

    void saveUser(User user);
    User findUser(int userId);

}
