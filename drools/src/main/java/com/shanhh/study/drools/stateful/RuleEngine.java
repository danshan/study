package com.shanhh.study.drools.stateful;

/**
 * @author dan.shan
 * @since 2015-03-13 16:48
 */
public interface RuleEngine {

    /**
     * 初始化规则引擎
     */
    public void initEngine();

    /**
     * 执行规则引擎
     */
    public void executeRuleEngine(Object object);

    <T> T execute(RuleEngineCallback<T> callback);
}
