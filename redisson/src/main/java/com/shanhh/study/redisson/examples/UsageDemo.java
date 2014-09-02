package com.shanhh.study.redisson.examples;

import com.shanhh.study.redisson.examples.beans.User;
import io.netty.util.concurrent.Future;
import org.apache.commons.lang3.RandomUtils;
import org.redisson.Redisson;
import org.redisson.core.RBucket;
import org.redisson.core.RMap;

import java.util.UUID;

/**
 * @author dan.shan
 * @since 2014-09-01 20:19
 */
public class UsageDemo {

    private static final String host = "10.128.38.14:6379";

    private User generateUser() {
        User user = new User();
        user.setUserId(RandomUtils.nextInt(1, 9999));
        user.setUsername(UUID.randomUUID().toString());
        return user;
    }

    /**
     * Distributed Object storage example
     */
    public void demoObjectStorage() {
        System.out.println("demoObjectStorage");
        Redisson redisson = new ConfigExample().getConfig(host);

        RBucket<User> bucket = redisson.getBucket("user");
        bucket.set(generateUser());

        System.out.println(bucket.get());
        redisson.shutdown();
    }

    public void demoObjectStorageAsync() {
        System.out.println("demoObjectStorageAsync");
        Redisson redisson = new ConfigExample().getConfig(host);

        RBucket<User> bucket = redisson.getBucket("user");
        bucket.setAsync(generateUser());

        System.out.println(bucket.get());
        redisson.shutdown();
    }

    /**
     * Distributed Map example
     */
    public void demoMap() {
        System.out.println("demoMap");
        Redisson redisson = new ConfigExample().getConfig(host);

        RMap<String, User> map = redisson.getMap("userMap");
        User prevObject = map.put("123", generateUser());
        User currentObject = map.putIfAbsent("323", generateUser());
        User obj = map.remove("123");

        System.out.println(redisson.getMap("userMap"));

        map.fastPut("321", generateUser());
        map.fastRemove("321");

        System.out.println(redisson.getMap("userMap"));
//        Future<User> putAsyncFuture = map.putAsync("321");
//        Future<Void> fastPutAsyncFuture = map.fastPutAsync("321");
//
//        map.fastPutAsync("321", generateUser());
//        map.fastRemoveAsync("321");

        redisson.shutdown();
    }

    public static void main(String[] args) {
        UsageDemo demo = new UsageDemo();

        demo.demoObjectStorage();
        demo.demoObjectStorageAsync();
        demo.demoMap();

        System.exit(0);
    }

}
