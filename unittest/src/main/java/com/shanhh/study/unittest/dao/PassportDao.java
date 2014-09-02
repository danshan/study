package com.shanhh.study.unittest.dao;

import com.shanhh.study.unittest.po.PassportPO;
import org.springframework.stereotype.Repository;

/**
 * @author dan.shan
 * @since 2014-04-27 下午7:05
 */
@Repository
public interface PassportDao {

    Integer getUserIdByUsername(String username);

    int savePassport(PassportPO passportPO);

}
