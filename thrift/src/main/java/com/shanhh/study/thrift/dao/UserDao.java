package com.shanhh.study.thrift.dao;

import com.shanhh.study.thrift.po.UserPO;
import org.springframework.stereotype.Repository;

/**
 * @author dan.shan
 * @since 2014-10-11 15:43
 */
@Repository
public interface UserDao {

    int create(UserPO user);

}
