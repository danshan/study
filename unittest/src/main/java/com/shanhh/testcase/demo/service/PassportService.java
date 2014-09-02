package com.shanhh.testcase.demo.service;

/**
 * @author dan.shan
 * @since 2014-04-27 下午5:12
 */
public interface PassportService {

    /**
     * register a new passport.
     * @param username
     * @param password
     * @return passport id.
     */
    int register(String username, String password);

}
