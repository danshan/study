package com.shanhh.study.drools.stateful;

import org.drools.runtime.StatefulKnowledgeSession;

/**
 * @author dan.shan
 * @since 2015-03-18 11:21
 */
public interface RuleEngineCallback<T> {
    T fireRules(StatefulKnowledgeSession ksession);
}
