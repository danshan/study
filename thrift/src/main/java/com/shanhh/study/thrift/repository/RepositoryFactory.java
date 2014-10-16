package com.shanhh.study.thrift.repository;

import com.shanhh.study.thrift.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author dan.shan
 * @since 2014-10-11 15:36
 */
@Component
public class RepositoryFactory {

    private static UserRepository userRepository;

    /**
     * create user and return userId
     * @param user
     * @return
     */
    public static int create(User user) {
        return userRepository.create(user);
    }

    @Autowired
    public static void setUserRepository(UserRepository userRepository) {
        RepositoryFactory.userRepository = userRepository;
    }
}
