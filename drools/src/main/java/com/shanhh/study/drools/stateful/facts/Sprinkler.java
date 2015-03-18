package com.shanhh.study.drools.stateful.facts;

import lombok.Data;

/**
 * @author dan.shan
 * @since 2015-03-18 10:41
 */
@Data
public class Sprinkler {
    private Room room;
    private boolean on;

    public Sprinkler(Room room) {
        this.room = room;

    }
}
