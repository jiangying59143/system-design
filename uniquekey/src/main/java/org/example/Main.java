package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

public class Main {
    private static final AtomicLong counter = new AtomicLong(System.currentTimeMillis());

    public static String generateUniqueKey() {
        UUID uniqueId = UUID.randomUUID();
        return uniqueId + "-" + counter.getAndIncrement();
    }

    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 10000; i++) {
            list.add(generateUniqueKey());
        }
        long endTime = System.currentTimeMillis();
        long sec = (endTime - startTime) / 1000;
        System.out.println(sec);


        for (int i = 0; i < list.size(); i++) {
            System.out.println("Unique ID: " + list.get(i));
        }
    }
}
