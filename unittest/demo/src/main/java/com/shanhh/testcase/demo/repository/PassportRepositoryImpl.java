package com.shanhh.testcase.demo.repository;

import com.shanhh.testcase.demo.dao.PassportDao;
import com.shanhh.testcase.demo.dao.PassportPO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * @author dan.shan
 * @since 2014-04-27 下午7:29
 */
@Repository
public class PassportRepositoryImpl implements PassportRepository {

    @Autowired
    private PassportDao passportDao;

    @Override
    public boolean checkUsernameExist(String username) {
        return passportDao.getUserIdByUsername(username) != null;
    }

    @Override
    public int savePassport(String username, String password) {
        PassportPO passportPO = new PassportPO();
        passportPO.setUsername(username);
        passportPO.setPassword(password);
        return passportDao.savePassport(passportPO);
    }
}
