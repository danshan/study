package com.shanhh.study.jedis.utils;

import org.apache.commons.pool.impl.GenericObjectPool;
import org.springframework.beans.factory.FactoryBean;
import redis.clients.jedis.JedisShardInfo;
import redis.clients.jedis.ShardedJedisPool;
import redis.clients.util.Hashing;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author dan.shan
 * @since 2014-09-02 18:51
 */
public class ShardJedisPool implements FactoryBean<ShardedJedisPool> {

    private ShardedJedisPool jedisPool;

    /**
     * Instantiates a new Redis service impl.
     *
     * @param poolConfig the pool config
     * @param hosts the hosts
     * @param timeout the timeout
     */
    public ShardJedisPool(final GenericObjectPool.Config poolConfig, final String hosts, int timeout) {
        List<String> hostList = Arrays.asList(hosts.split(","));
        List<JedisShardInfo> shardInfos = new ArrayList<JedisShardInfo>();
        JedisShardInfo jedisShardInfo;
        for (String oneHost : hostList) {
            String[] items = oneHost.split(":");

            jedisShardInfo = new JedisShardInfo(items[0], Integer.valueOf(items[1]), timeout);
            shardInfos.add(jedisShardInfo);
        }
        jedisPool = new ShardedJedisPool(poolConfig, shardInfos, Hashing.MURMUR_HASH);
    }

    @Override
    public ShardedJedisPool getObject() throws Exception {
        return jedisPool;
    }

    @Override
    public Class<?> getObjectType() {
        return jedisPool.getClass();
    }

    @Override
    public boolean isSingleton() {
        return true;
    }
}
