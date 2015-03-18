package com.shanhh.study.drools.demo;

import org.drools.command.Command;

import java.util.List;

/**
 * @author dan.shan
 * @since 2015-03-13 16:48
 */
public interface VoteRuleEngine {

    /**
     * 初始化规则引擎
     */
    public void initEngine();

    /**
     * 刷新规则引擎中的规则
     */
    public void refreshEnginRule();

    /**
     * 执行规则引擎
     * @param domain 投票Fact
     */
    public void executeRuleEngine(final VoteDomain domain);
    public void executeRuleEngine(final Iterable<VoteDomain> domains);

    public void executeRuleEngine(final List<Command> commands);

}
