package com.shanhh.study.jedis.service;

import com.shanhh.study.jedis.bean.User;

/**
 * @author dan.shan
 * @since 2014-09-03 15:07
 */
public interface SingleService {

    void saveUser(User user);
    User findUser(int userId);

}
