package com.shanhh.study.drools.stateful;

import org.apache.poi.ss.formula.functions.T;
import org.drools.KnowledgeBase;
import org.drools.builder.*;
import org.drools.io.impl.ClassPathResource;
import org.drools.runtime.StatefulKnowledgeSession;
import org.drools.runtime.StatelessKnowledgeSession;
import org.springframework.util.CollectionUtils;

/**
 * @author dan.shan
 * @since 2015-03-13 16:55
 */
public class RuleEngineImpl implements RuleEngine {

    private KnowledgeBase knowledgeBase;

    @Override
    public void initEngine() {
        System.setProperty("drools.dateformat", "yyyy-MM-dd HH:mm:ss");
        KnowledgeBuilder kb = KnowledgeBuilderFactory.newKnowledgeBuilder();
        kb.add(new ClassPathResource("stateful/drl/fireAlarm.drl"), ResourceType.DRL);

        KnowledgeBuilderErrors kbErrors = kb.getErrors();
        if (kbErrors.size() > 0) {
            for (KnowledgeBuilderError kbError : kbErrors) {
                System.out.println("parse error:" + kbError.getMessage());
            }
            throw new IllegalArgumentException("can not parse knowledge." + kbErrors.toString());
        }

        knowledgeBase = KnowledgeFactory.getKnowledgeBase();
        knowledgeBase.addKnowledgePackages(kb.getKnowledgePackages());
    }

    @Override
    public void executeRuleEngine(Object object) {
        if (CollectionUtils.isEmpty(knowledgeBase.getKnowledgePackages())) {
            throw new IllegalStateException("need to init first");
        }

        StatefulKnowledgeSession ksession = knowledgeBase.newStatefulKnowledgeSession();
        ksession.insert(object);

        ksession.fireAllRules();
        ksession.dispose();
    }

    @Override
    public <T> T execute(RuleEngineCallback<T> callback) {
        StatefulKnowledgeSession ksession = knowledgeBase.newStatefulKnowledgeSession();
        T result = callback.fireRules(ksession);
        ksession.fireAllRules();
        ksession.dispose();
        return result;
    }

}
