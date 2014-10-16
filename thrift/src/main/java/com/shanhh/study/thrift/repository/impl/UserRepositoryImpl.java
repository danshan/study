package com.shanhh.study.thrift.repository.impl;

import com.shanhh.study.thrift.dao.UserDao;
import com.shanhh.study.thrift.domain.User;
import com.shanhh.study.thrift.po.UserPO;
import com.shanhh.study.thrift.repository.UserRepository;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

/**
 * @author dan.shan
 * @since 2014-10-11 15:40
 */
@Repository
public class UserRepositoryImpl implements UserRepository {

    @Resource
    private UserDao userDao;

    @Override
    public int create(User user) {
        UserPO po = new UserPO(user);
        return userDao.create(po);
    }

}
