package com.shanhh.study.jedis.demo;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.exceptions.JedisDataException;

import java.util.List;
import java.util.UUID;

/**
 * @author dan.shan
 * @since 2014-09-04 12:01
 */
@Service
public class StringDemo {

    private JedisPool jedisPool;

    public void setJedisPool(JedisPool jedisPool) {
        this.jedisPool = jedisPool;
    }

    /**
     * SET / GET / APPEND / STRLEN
     */
    private void demoSetGetAppendStrlen() {
        System.out.println("demo SET / GET / APPEND / STRLEN");
        String key = UUID.randomUUID().toString();

        Jedis jedis = jedisPool.getResource();

        System.out.println(jedis.exists(key)); // false
        System.out.println(jedis.append(key, "fuck")); // 4
        System.out.println(jedis.append(key, " gfw")); // 8
        System.out.println(jedis.get(key)); // fuck gfw
        System.out.println(jedis.set(key, "fuck gov")); // OK
        System.out.println(jedis.get(key)); // fuck gov
        System.out.println(jedis.strlen(key)); // 8

        jedis.del(key);
        jedisPool.returnResource(jedis);
    }

    /**
     * INCR/DECR/INCRBY/DECRBY
     */
    private void demoIncrDecrIncrbyDecrby() {
        System.out.println("demo INCR / DECR / INCRBY / DECRBY");
        String key = UUID.randomUUID().toString();

        Jedis jedis = jedisPool.getResource();

        System.out.println(jedis.set(key, "20")); // OK
        System.out.println(jedis.incr(key)); // 21
        System.out.println(jedis.decr(key)); // 20
        System.out.println(jedis.del(key)); // 1

        System.out.println(jedis.decr(key)); // -1
        System.out.println(jedis.del(key)); // 1

        System.out.println(jedis.set(key, "fuck gfw")); // OK
        try {
            System.out.println(jedis.incr(key));
        } catch (JedisDataException e) {
            System.out.println(e.getMessage()); // ERR value is not an integer or out of range
        }
        System.out.println(jedis.set(key, "10")); // OK
        System.out.println(jedis.decrBy(key, 5)); // 5
        System.out.println(jedis.incrBy(key, 10)); // 15

        jedis.del(key);
        jedisPool.returnResource(jedis);
    }

    /**
     * GETSET
     */
    private void demoGetSet() {
        System.out.println("demo GETSET");
        String key = UUID.randomUUID().toString();

        Jedis jedis = jedisPool.getResource();

        System.out.println(jedis.incr(key)); // 1
        System.out.println(jedis.getSet(key, "0")); // 1
        System.out.println(jedis.get(key)); // 0

        jedis.del(key);
        jedisPool.returnResource(jedis);
    }

    /**
     * SETEX
     */
    private void demoSetex() throws InterruptedException {
        System.out.println("demo SETEX");
        String key = UUID.randomUUID().toString();

        Jedis jedis = jedisPool.getResource();

        System.out.println(jedis.setex(key, 5, "fuck gfw")); // OK
        for (int i = 0; i < 8; i++) {
            System.out.println("sec " + i +": " + jedis.get(key)); // sec 0: fuck gfw || sec 7: null
            System.out.println("sec " + i +": " + jedis.ttl(key)); // sec 0: 5 || sec 7: -1
            Thread.sleep(1000);
        }

        jedis.del(key);
        jedisPool.returnResource(jedis);
    }

    /**
     * SETNX
     */
    private void demoSetnx() {
        System.out.println("demo SETNX");
        String key = UUID.randomUUID().toString();

        Jedis jedis = jedisPool.getResource();

        System.out.println(jedis.setnx(key, "fuck gfw")); // 1
        System.out.println(jedis.setnx(key, "fuck gov")); // 0
        System.out.println(jedis.get(key)); // fuck gfw

        jedis.del(key);
        jedisPool.returnResource(jedis);
    }

    /**
     * SETRANGE / GETRANGE
     */
    private void demoSetrangeGetrange() {
        System.out.println("demo SETRANGE / GETRANGE");
        String key = UUID.randomUUID().toString();

        Jedis jedis = jedisPool.getResource();

        System.out.println(jedis.set(key, "fuck gfw")); // OK
        System.out.println(jedis.get(key)); // fuck gfw

        System.out.println(jedis.setrange(key, 5L, "gov")); // 8
        System.out.println(jedis.get(key)); // fuck gov

        System.out.println(jedis.setrange(key, 20L, " and gfw")); // 28
        System.out.println(jedis.get(key)); // fuck gov and gfw

        jedis.del(key);

        System.out.println(jedis.setrange(key, 5L, "fuck")); // 9
        System.out.println(jedis.get(key)); // fuck
        System.out.println(jedis.set(key, "0123456789")); // OK
        System.out.println(jedis.getrange(key, 1, 2)); // 12
        System.out.println(jedis.getrange(key, 1, 20)); // 123456789

        jedis.del(key);
        jedisPool.returnResource(jedis);
    }

    /**
     * SETBIT / GETBIT
     */
    private void demoSetbitGetbit() {
        System.out.println("demo SETBIT / GETBIT");
        String key = UUID.randomUUID().toString();

        Jedis jedis = jedisPool.getResource();
        System.out.println(jedis.setbit(key, 7, true)); // false 返回原有BIT值0
        System.out.println(jedis.get(key)); // "", 这个...不知道为什么不生效
        System.out.println(jedis.setbit(key, 6, true)); // false
        System.out.println(jedis.get(key));
        System.out.println(jedis.getbit(key, 6)); // true 这里倒是有值
        System.out.println(jedis.getbit(key, 10)); // false

        jedis.del(key);
        jedisPool.returnResource(jedis);
    }

    /**
     * MSET / MGET / MSETNX
     */
    private void demoMsetMgetMsetnx() {
        System.out.println("demo MSET / MGET / MSETNX");
        String key1 = UUID.randomUUID().toString();
        String key2 = UUID.randomUUID().toString();
        String key3 = UUID.randomUUID().toString();
        String key4 = UUID.randomUUID().toString();
        String key5 = UUID.randomUUID().toString();

        Jedis jedis = jedisPool.getResource();

        System.out.println(jedis.mset(key1, "fuck gfw", key2, "fuck gov"));
        List<String> values = jedis.mget(key1, UUID.randomUUID().toString(), key2, UUID.randomUUID().toString()); // OK
        for (String value : values) {
            System.out.println("value: " + value); // fuck gfw | null | fuck gov | null
        }

        System.out.println(jedis.msetnx(key3, "fuck gfw", key4, "fuck gov")); // 1
        values = jedis.mget(key3, key4);
        for (String value : values) {
            System.out.println("value: " + value); // fuck gfw | fuck gov
        }

        System.out.println(jedis.msetnx(key3, "fuck you", key5, "fuck me")); // 0
        values = jedis.mget(key3, key4);
        for (String value : values) {
            System.out.println("value: " + value); // fuck gfw | fuck gov
        }

        jedis.del(key1);
        jedis.del(key2);
        jedis.del(key3);
        jedis.del(key4);
        jedis.del(key5);
        jedisPool.returnResource(jedis);
    }

    public static void main(String[] args) throws InterruptedException {
        ApplicationContext factory = new ClassPathXmlApplicationContext("classpath:spring/applicationContext.xml");
        JedisPool jedisPool = factory.getBean(JedisPool.class);

        StringDemo demo = new StringDemo();
        demo.setJedisPool(jedisPool);

        demo.demoSetGetAppendStrlen();
        demo.demoIncrDecrIncrbyDecrby();
        demo.demoGetSet();
        demo.demoSetex();
        demo.demoSetnx();
        demo.demoSetrangeGetrange();
        demo.demoSetbitGetbit();
        demo.demoMsetMgetMsetnx();
    }

}
