package com.shanhh.study.jedis.service.impl;

import com.shanhh.study.jedis.service.MonitorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisShardInfo;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * @author dan.shan
 * @since 2014-09-03 11:18
 */
@Service
public class MonitorServiceImpl implements MonitorService {

    @Autowired
    private ShardedJedisPool jedisPool;

    @Override
    public void shardInfo() {
        ShardedJedis shardJedis = null;
        try {
            shardJedis = jedisPool.getResource();
            Collection<JedisShardInfo> shardInfos = shardJedis.getAllShardInfo();
            Map<String, String> map = new HashMap<String, String>();
            for (JedisShardInfo jedisInfo : shardInfos) {
                Jedis jedis = null;
                String key = jedisInfo.getHost() + ":" + jedisInfo.getPort();
                try {
                    if (map.containsKey(key)) {
                        continue;
                    }
                    jedis = jedisInfo.createResource();
                    jedis.ping();
                    map.put(key, "success");
                } catch (Exception ex) {
                    map.put(key, "fail");
                } finally {
                    if (jedis != null) {
                        jedis.disconnect();
                    }
                }
            }

            for (Map.Entry<String, String> entry : map.entrySet()) {
                System.out.println(entry.getKey() + ": " + entry.getValue());
            }
        } finally {
            if (shardJedis != null) {
                jedisPool.returnResource(shardJedis);
            }
        }
    }

}
