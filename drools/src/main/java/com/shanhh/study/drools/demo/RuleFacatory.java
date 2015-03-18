package com.shanhh.study.drools.demo;

import org.drools.RuleBase;
import org.drools.RuleBaseFactory;

/**
 * RuleBaseFacatory 单实例RuleBase生成工具
 * @author dan.shan
 */
public class RuleFacatory {
    private static RuleBase ruleBase;
	
	public static RuleBase getRuleBase(){
		return null != ruleBase ? ruleBase : RuleBaseFactory.newRuleBase();
	}
}