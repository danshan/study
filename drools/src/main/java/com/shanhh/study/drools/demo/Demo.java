package com.shanhh.study.drools.demo;

import org.drools.command.Command;
import org.drools.command.CommandFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * @author dan.shan
 * @since 2015-03-13 16:46
 */
public class Demo {

    public static void main(String[] args) throws IOException {
        VoteRuleEngine pointRuleEngine = new VoteRuleEngineImpl();
        while (true) {
            InputStream is = System.in;
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            String input = br.readLine();

            if (null != input && "s".equals(input)) {
                System.out.println("初始化规则引擎...");
                pointRuleEngine.initEngine();
                System.out.println("初始化规则引擎结束.");
            } else if ("e".equals(input)) {
                System.out.println("execute Iterable");
                pointRuleEngine.executeRuleEngine(buildTestDomains());

                System.out.println("execute command");

                pointRuleEngine.executeRuleEngine(buildTestCommands());
            } else if ("r".equals(input)) {
                System.out.println("刷新规则文件...");
                pointRuleEngine.refreshEnginRule();
                System.out.println("刷新规则文件结束.");
            }

        }
    }

    private static List<Command> buildTestCommands() {
        List<Command> cmds = new ArrayList<Command>();
        cmds.add(CommandFactory.newInsert(buildDomainBL1Day()));
        cmds.add(CommandFactory.newInsert(buildDomainBL1Day30Mins()));
        cmds.add(CommandFactory.newInsert(buildDomainBL30Mins()));
        return cmds;

    }

    private static List<VoteDomain> buildTestDomains() {
        List<VoteDomain> list = new ArrayList<VoteDomain>();
        list.add(buildDomainBL30Mins());
        list.add(buildDomainBL1Day());
        list.add(buildDomainBL1Day30Mins());
        return list;
    }

    private static VoteDomain buildDomainBL1Day30Mins() {
        VoteDomain domain = new VoteDomain();
        domain.setUserId(1);
        domain.setPicId(1);
        domain.setCountIn1Day(5000);
        domain.setCountIn30Mins(500);
        return domain;
    }

    private static VoteDomain buildDomainBL1Day() {
        VoteDomain domain = new VoteDomain();
        domain.setUserId(2);
        domain.setPicId(2);
        domain.setCountIn1Day(5000);
        return domain;
    }

    private static VoteDomain buildDomainBL30Mins() {
        VoteDomain domain = new VoteDomain();
        domain.setUserId(3);
        domain.setPicId(3);
        domain.setCountIn30Mins(500);
        return domain;
    }

}
