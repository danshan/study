package com.shanhh.study.thrift.repository;

import com.shanhh.study.thrift.domain.User;

/**
 * @author dan.shan
 * @since 2014-10-11 15:39
 */
public interface UserRepository {

    public int create(User user);

}
