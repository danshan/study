package com.shanhh.study.unittest.service;

import com.shanhh.study.unittest.repository.PassportRepository;
import com.shanhh.study.unittest.utils.RegexUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author dan.shan
 * @since 2014-04-27 下午5:19
 */
@Service
public class PassportServiceImpl implements PassportService {

    @Autowired
    PassportRepository passportRepository;

    @Override
    public int register(String username, String password) {
        if (username == null) {
            throw new IllegalArgumentException("username cannot be null.");
        } else if (password == null) {
            throw new IllegalArgumentException("password cannot be null.");
        }

        validUsername(username);
        return passportRepository.savePassport(username, password);
    }

    private void validUsername(String username) {
        if (!RegexUtils.isEmail(username) && !RegexUtils.isMobile(username)) {
            throw new IllegalArgumentException("username format incorrect, username=" + username);
        }

        if (passportRepository.checkUsernameExist(username)) {
            throw new IllegalStateException("username already exists, username=" + username);
        }
    }
}
