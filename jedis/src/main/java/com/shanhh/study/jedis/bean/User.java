package com.shanhh.study.jedis.bean;

import java.io.Serializable;

/**
 * @author dan.shan
 * @since 2014-09-02 19:39
 */
public class User implements Serializable {

    private int userId;
    private String username;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
