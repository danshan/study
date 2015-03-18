package com.shanhh.study.drools.demo;

import lombok.Data;

/**
 * 投票的记录
 * @author dan.shan
 * @since 2015-03-13 16:50
 */
@Data
public class VoteDomain {

    private int userId;
    private int picId;
    /**
     * 30 分钟内的投票次数
     */
    private int countIn30Mins;

    /**
     * 60 分钟内的投票次数
     */
    private int countIn60Mins;

    /**
     * 60 分钟内的投票次数
     */
    private int countIn1Day;

    /**
     * 总共报警次数
     */
    private int waringTimesTotal;

    /**
     * 连续共报警次数
     */
    private int waringTimesRecent;

}
