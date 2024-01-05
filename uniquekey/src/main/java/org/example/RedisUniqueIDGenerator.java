package org.example;

import redis.clients.jedis.Jedis;

import java.util.ArrayList;
import java.util.List;

public class RedisUniqueIDGenerator {
    private Jedis jedis;

    public RedisUniqueIDGenerator() {
        this.jedis = new Jedis("localhost", 6379);
    }

    public long generateUniqueID() {
        return jedis.incr("my_increment_key");
    }

    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        List<Long> list = new ArrayList<>();
        RedisUniqueIDGenerator idGenerator = new RedisUniqueIDGenerator();
        for (int i = 0; i < 10000; i++) {
           list.add(idGenerator.generateUniqueID());
        }
        long endTime = System.currentTimeMillis();
        long sec = (endTime - startTime) / 1000;
        System.out.println(sec);
        for (int i = 0; i < list.size(); i++) {
            System.out.println("Unique ID: " + list.get(i));
        }

    }
}
