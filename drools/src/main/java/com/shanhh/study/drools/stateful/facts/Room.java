package com.shanhh.study.drools.stateful.facts;

import lombok.Data;

/**
 * @author dan.shan
 * @since 2015-03-18 10:41
 */
@Data
public class Room {
    private String name;

    public Room(String name) {
        this.name = name;
    }
}
