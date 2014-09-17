package com.shanhh.study.jedis.demo;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;
import redis.clients.jedis.BinaryClient;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.exceptions.JedisDataException;

import java.util.UUID;

/**
 * @author dan.shan
 * @since 2014-09-17 12:03
 */
@Service
public class ListDemo {

    private JedisPool jedisPool;

    public void setJedisPool(JedisPool jedisPool) {
        this.jedisPool = jedisPool;
    }

    /**
     * LPUSH/LPUSHX/LRANGE
     */
    private void demoLpushLpushxLrange() {
        System.out.println("demo LPUSH/LPUSHX/LRANGE");
        String key = UUID.randomUUID().toString();
        String key2 = UUID.randomUUID().toString();

        Jedis jedis = jedisPool.getResource();

        System.out.println(jedis.lpush(key, "a", "b", "c", "d")); // 4
        System.out.println(jedis.lrange(key, 0, 2)); // [d, c, b]

        // 取链表中的全部元素，其中0表示第一个元素，-1表示最后一个元素。
        System.out.println(jedis.lrange(key, 0, -1)); // [d, c, b, a]

        // key2键此时并不存在，因此该命令将不会进行任何操作，其返回值为0。
        System.out.println(jedis.lpushx(key2, "e")); // 0
        System.out.println(jedis.lrange(key2, 0, -1)); // []
        // key键此时已经存在，所以该命令插入成功，并返回链表中当前元素的数量。
        System.out.println(jedis.lpush(key, "e")); // 5
        System.out.println(jedis.lrange(key, 0, 0)); // [e]

        jedis.del(key, key2);
        jedisPool.returnResource(jedis);
    }

    /**
     * LLPOP/LLEN
     */
    private void demoLpopLlen() {
        System.out.println("demo LLPOP/LLEN");
        String key = UUID.randomUUID().toString();

        Jedis jedis = jedisPool.getResource();

        System.out.println(jedis.lpush(key, "a", "b", "c", "d")); // 4
        System.out.println(jedis.lpop(key)); // d
        System.out.println(jedis.lpop(key)); // c
        System.out.println(jedis.llen(key)); // 2

        jedis.del(key);
        jedisPool.returnResource(jedis);
    }

    /**
     * LREM/LSET/LINDEX/LTRIM
     */
    private void demoLremLsetLindexLtrim() {
        System.out.println("demo LREM/LSET/LINDEX/LTRIM");
        String key = UUID.randomUUID().toString();

        Jedis jedis = jedisPool.getResource();

        System.out.println(jedis.lpush(key, "a", "b", "c", "d", "a", "c")); // 6
        // 从头部(left)向尾部(right)变量链表，删除2个值等于a的元素，返回值为实际删除的数量。
        System.out.println(jedis.lrem(key, 2, "a")); // 2
        System.out.println(jedis.lrange(key, 0, -1)); // [c, d, c, b]

        System.out.println(jedis.lindex(key, 1)); // d
        System.out.println(jedis.lset(key, 1, "e")); // OK
        System.out.println(jedis.lindex(key, 1)); // e

        // 索引值6超过了链表中元素的数量，该命令返回nil。
        System.out.println(jedis.lindex(key, 6)); // null

        // 设置的索引值6超过了链表中元素的数量，设置失败，该命令返回错误信息。
        try {
            System.out.println(jedis.lset(key, 6, "h"));
        } catch (JedisDataException e) {
            System.out.println(e.getMessage()); // ERR index out of range
        }
        // 仅保留索引值0到2之间的3个元素，注意第0个和第2个元素均被保留。
        System.out.println(jedis.ltrim(key, 0, 2)); // OK
        System.out.println(jedis.lrange(key, 0, -1)); // [c, e, c]

        jedis.del(key);
        jedisPool.returnResource(jedis);
    }

    private void demoLinsert() {
        System.out.println("demo LREM/LSET/LINDEX/LTRIM");
        String key = UUID.randomUUID().toString();

        Jedis jedis = jedisPool.getResource();

        System.out.println(jedis.lpush(key, "a", "b", "c", "d", "e")); // 5
        // a的前面插入新元素
        System.out.println(jedis.linsert(key, BinaryClient.LIST_POSITION.BEFORE, "a", "a1")); // 6
        System.out.println(jedis.lindex(key, 4)); // a1
        // 在e的后面插入新元素
        System.out.println(jedis.linsert(key, BinaryClient.LIST_POSITION.AFTER, "e", "e2")); // 7
        System.out.println(jedis.lindex(key, 1)); // e2

        // 在不存在的元素之前或之后插入新元素，该命令操作失败，并返回-1。
        System.out.println(jedis.linsert(key, BinaryClient.LIST_POSITION.AFTER, "k", "a")); // -1
        // 为不存在的Key插入新元素，该命令操作失败，返回0。
        System.out.println(jedis.linsert(UUID.randomUUID().toString(), BinaryClient.LIST_POSITION.AFTER, "a", "a2")); // 0

        jedis.del(key);
        jedisPool.returnResource(jedis);
    }

    /**
     * RPUSH/RPUSHX/RPOP/RPOPLPUSH
     */
    private void demoRpushRpushxRpopRpoplpush() {
        System.out.println("RPUSH/RPUSHX/RPOP/RPOPLPUSH");
        String key = UUID.randomUUID().toString();
        String key2 = UUID.randomUUID().toString();

        Jedis jedis = jedisPool.getResource();

        // 从链表的尾部插入参数中给出的values，插入顺序是从左到右依次插入
        System.out.println(jedis.rpush(key, "a", "b", "c", "d")); // 4
        System.out.println(jedis.lrange(key, 0, -1)); // [a, b, c, d]

        System.out.println(jedis.rpushx(key, "e")); // 5
        System.out.println(jedis.lindex(key, 4)); // e

        // 由于key2键并不存在，因此该命令不会插入数据，其返回值为0。
        System.out.println(jedis.rpushx(key2, "e")); // 0

        // 在执行rpoplpush命令前，先看一下key中链表的元素有哪些，注意他们的位置关系。
        System.out.println(jedis.lrange(key, 0, -1)); // [a, b, c, d, e]
        // 将mykey的尾部元素e弹出，同时再插入到key2的头部(原子性的完成这两步操作)。
        System.out.println(jedis.rpoplpush(key, key2)); // e
        System.out.println(jedis.lrange(key, 0, -1)); // [a, b, c, d]
        System.out.println(jedis.lrange(key2, 0, -1)); // [e]

        // 将source和destination设为同一键，将mykey中的尾部元素移到其头部。
        System.out.println(jedis.rpoplpush(key, key)); // d
        System.out.println(jedis.lrange(key, 0, -1)); // [d, a, b, c]

        jedis.del(key);
        jedisPool.returnResource(jedis);
    }

    public static void main(String[] args) {
        ApplicationContext factory = new ClassPathXmlApplicationContext("classpath:spring/applicationContext.xml");
        JedisPool jedisPool = factory.getBean(JedisPool.class);

        ListDemo demo = new ListDemo();
        demo.setJedisPool(jedisPool);

        demo.demoLpushLpushxLrange();
        demo.demoLpopLlen();
        demo.demoLremLsetLindexLtrim();
        demo.demoLinsert();
        demo.demoRpushRpushxRpopRpoplpush();
    }

}
