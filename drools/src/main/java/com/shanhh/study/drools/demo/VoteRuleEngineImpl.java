package com.shanhh.study.drools.demo;

import org.drools.KnowledgeBase;
import org.drools.builder.*;
import org.drools.command.Command;
import org.drools.command.CommandFactory;
import org.drools.definition.KnowledgePackage;
import org.drools.io.impl.ClassPathResource;
import org.drools.runtime.StatelessKnowledgeSession;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * @author dan.shan
 * @since 2015-03-13 16:55
 */
public class VoteRuleEngineImpl implements VoteRuleEngine {

    private KnowledgeBase knowledgeBase;

    @Override
    public void initEngine() {
        System.setProperty("drools.dateformat", "yyyy-MM-dd HH:mm:ss");
        KnowledgeBuilder kb = KnowledgeBuilderFactory.newKnowledgeBuilder();
        kb.add(new ClassPathResource("demo/drl/vote.drl"), ResourceType.DRL);

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
    public void refreshEnginRule() {
        for (KnowledgePackage knowledgePackage : knowledgeBase.getKnowledgePackages()) {
            knowledgeBase.removeKnowledgePackage(knowledgePackage.getName());
        }
        initEngine();
    }

    @Override
    public void executeRuleEngine(VoteDomain domain) {
        if (CollectionUtils.isEmpty(knowledgeBase.getKnowledgePackages())) {
            throw new IllegalStateException("need to init first");
        }

        StatelessKnowledgeSession statelessKnowledgeSession = knowledgeBase.newStatelessKnowledgeSession();
        statelessKnowledgeSession.execute(domain);

    }

    @Override
    public void executeRuleEngine(Iterable<VoteDomain> domains) {
        if (CollectionUtils.isEmpty(knowledgeBase.getKnowledgePackages())) {
            throw new IllegalStateException("need to init first");
        }

        StatelessKnowledgeSession statelessKnowledgeSession = knowledgeBase.newStatelessKnowledgeSession();
        statelessKnowledgeSession.execute(domains);

    }

    @Override
    public void executeRuleEngine(List<Command> commands) {
        if (CollectionUtils.isEmpty(knowledgeBase.getKnowledgePackages())) {
            throw new IllegalStateException("need to init first");
        }

        StatelessKnowledgeSession statelessKnowledgeSession = knowledgeBase.newStatelessKnowledgeSession();
        statelessKnowledgeSession.execute(CommandFactory.newBatchExecution(commands));

    }
}
