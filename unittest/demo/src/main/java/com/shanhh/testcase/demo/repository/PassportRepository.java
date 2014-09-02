package com.shanhh.testcase.demo.repository;

/**
 * @author dan.shan
 * @since 2014-04-27 下午7:05
 */
public interface PassportRepository {
    /**
     * checking if username exist.
     * @param username
     * @return if username exist.
     */
    boolean checkUsernameExist(String username);

    /**
     * register a new passport.
     * @param username
     * @param password
     * @return passport id
     */
    int savePassport(String username, String password);
}
