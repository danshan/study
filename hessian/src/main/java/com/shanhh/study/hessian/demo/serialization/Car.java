package com.shanhh.study.hessian.demo.serialization;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author dan.shan
 * @since 2014-09-18 19:59
 */
@Data
@AllArgsConstructor
@ToString
public class Car implements Serializable {
    private Model model;
    private Color color;
    private int year;

    public enum Model {
        CIVIC,
        EDSEL,
        MODEL_T,
    }

    public enum Color {
        BLACK,
        GREEN,
        BLUE,
    }
}


