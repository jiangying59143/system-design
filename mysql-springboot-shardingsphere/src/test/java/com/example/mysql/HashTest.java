package com.example.mysql;

import com.google.common.hash.HashFunction;
import com.google.common.hash.Hashing;

import java.nio.charset.StandardCharsets;
import java.util.*;

public class HashTest {

    static Random random = new Random();

    static HashFunction hashFunction = Hashing.sha256();

    static List<Long> data = new ArrayList<>();

    static{
        for (int i = 0; i < 1000; i++) {
            Long id = random.nextLong();
            data.add(id);
        }
    }

    static int actualNodes = 32;




    /**
     * 普通Hash算法
     */

    public static void normalHash() {
        Map<Integer, Integer> map = new HashMap<>();
        for (Long id : data) {
            int code = (int) (id % Integer.MAX_VALUE);
            int key = Math.abs(code % actualNodes);
            Integer valueNums = map.get(key);
            if (valueNums != null) {
                map.put(key, ++valueNums);
            } else {
                map.put(key, 1);
            }
        }
        System.out.println("普通Hash算法分布情况：\n" + map);
    }

    /**
     * guava实现一致性Hash算法
     */
    public static void guavaConsistentHash() {
        Map<Integer, Integer> map = new HashMap<>();
        for (Long id : data) {
            int key = Hashing.consistentHash(hashFunction.hashLong(id), actualNodes);
            Integer valueNums = map.get(key);
            if (valueNums != null) {
                map.put(key, ++valueNums);
            } else {
                map.put(key, 1);
            }
        }
        System.out.println("\nguava实现一致性Hash算法分布情况：\n" + map);
    }

    /**
     * TODO 添加节点需要考虑是
     * 1. 缓解一个服务器的流量 还是 所有服务器的流量
     * 2. 如果考虑问题 1, 就要涉及迁移数据的多少
     */
    public static void MyConsistentHash() {
        Map<Integer, Integer> map = new HashMap<>();
        for (Long id : data) {
            int virtualNodeKey = hashFunction.hashLong(id).asInt();
            int key = Math.abs(virtualNodeKey % actualNodes);
            Integer valueNums = map.get(key);
            if (valueNums != null) {
                map.put(key, ++valueNums);
            } else {
                map.put(key, 1);
            }
        }
        System.out.println("\nMy实现一致性Hash算法分布情况：\n" + map);
    }

    public static void main(String[] args) {
        normalHash();
        guavaConsistentHash();
        MyConsistentHash();
    }
}

