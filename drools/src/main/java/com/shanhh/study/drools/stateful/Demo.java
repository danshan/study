package com.shanhh.study.drools.stateful;

import com.shanhh.study.drools.stateful.facts.Fire;
import com.shanhh.study.drools.stateful.facts.Room;
import com.shanhh.study.drools.stateful.facts.Sprinkler;
import org.drools.runtime.StatefulKnowledgeSession;
import org.drools.runtime.rule.FactHandle;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author dan.shan
 * @since 2015-03-13 16:46
 */
public class Demo {
    private static Map<String, Room> name2room = new HashMap<String, Room>();

    public static void main(String[] args) throws IOException {
        RuleEngine ruleEngine = new RuleEngineImpl();
        System.out.println("初始化规则引擎...");
        ruleEngine.initEngine();
        System.out.println("初始化规则引擎结束.");

        final String[] names = new String[]{"kitchen", "bedroom", "office", "livingroom"};

        // Everything is ok
        ruleEngine.execute(new RuleEngineCallback<Object>() {
            @Override
            public Object fireRules(StatefulKnowledgeSession ksession) {
                for (String name : names) {
                    Room room = new Room(name);
                    name2room.put(name, room);
                    ksession.insert(room);
                    Sprinkler sprinkler = new Sprinkler(room);
                    ksession.insert(sprinkler);
                }
                return null;
            }
        });


        // Raise the alarm
        ruleEngine.execute(new RuleEngineCallback<Object>() {
            @Override
            public Object fireRules(StatefulKnowledgeSession ksession) {
                Fire kitchenFire = new Fire(name2room.get("kitchen"));
                Fire officeFire = new Fire(name2room.get("office"));


                Sprinkler kitchenSprinkler = new Sprinkler(kitchenFire.getRoom());
                Sprinkler officeSprinkler = new Sprinkler(officeFire.getRoom());
                FactHandle kitchenFireHandle = ksession.insert(kitchenFire);
                FactHandle officeFireHandle = ksession.insert(officeFire);
                ksession.insert(kitchenFire);
                ksession.insert(kitchenSprinkler);
                ksession.insert(officeFire);
                ksession.insert(officeSprinkler);

                // ksession.retract( kitchenFireHandle );
                // ksession.retract( officeFireHandle );
                return null;
            }
        });

    }
}

