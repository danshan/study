package com.shanhh.study.drools.stateful.facts;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author dan.shan
 * @since 2015-03-18 10:42
 */
@Data
@NoArgsConstructor
public class Fire {
    private Room room;

    public Fire(Room room) {
        this.room = room;
    }
}
