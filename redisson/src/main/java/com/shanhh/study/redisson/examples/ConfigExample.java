package com.shanhh.study.redisson.examples;

import org.redisson.Config;
import org.redisson.MasterSlaveServersConfig;
import org.redisson.Redisson;
import org.redisson.SentinelServersConfig;

import java.util.List;

/**
 * @author dan.shan
 * @since 2014-09-01 19:05
 */
public class ConfigExample {

    // single server

    /**
     * connects to default Redis server 127.0.0.1:6379
     * @return
     */
    public Redisson getConfig() {
        return Redisson.create();
    }

    /**
     * connects to single Redis server via Config
     * @param host ip:port
     * @return
     */
    public Redisson getConfig(String host) {
        Config config = new Config();
        config.useSingleServer().setAddress(host);
        return Redisson.create(config);
    }

    // master / slave servers

    /**
     * master / slave servers
     * @param master
     * @param slaves
     * @return
     */
    public Redisson getConfig(String master, List<String> slaves) {
        Config config = new Config();

        MasterSlaveServersConfig masterSlaveServersConfig = config.useMasterSlaveConnection();
        masterSlaveServersConfig.setMasterAddress(master);

        if (slaves != null) {
            for (String slave : slaves) {
                masterSlaveServersConfig.addSlaveAddress(slave);
            }
        }
        return Redisson.create(config);
    }


    /**
     * sentinel servers
     * @param masterName
     * @param sentinels
     * @return
     */
    public Redisson getSentinelConfig(String masterName, List<String> sentinels) {
        Config config = new Config();

        SentinelServersConfig sentinelServersConfig = config.useSentinelConnection();
        sentinelServersConfig.setMasterName(masterName);

        if (sentinels!= null) {
            for (String sentinel : sentinels) {
                sentinelServersConfig.addSentinelAddress(sentinel);
            }
        }
        return Redisson.create(config);
    }

}
