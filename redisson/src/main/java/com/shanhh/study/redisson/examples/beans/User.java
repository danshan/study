package com.shanhh.study.redisson.examples.beans;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author dan.shan
 * @since 2014-09-01 20:54
 */
@Data
@ToString
public class User implements Serializable {

    private int userId;
    private String username;

}
