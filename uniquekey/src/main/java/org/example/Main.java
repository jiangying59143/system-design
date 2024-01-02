package org.example;

import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

public class Main {
    private static final AtomicLong counter = new AtomicLong(System.currentTimeMillis());

    public static String generateUniqueKey() {
        UUID uniqueId = UUID.randomUUID();
        return uniqueId + "-" + counter.getAndIncrement();
    }

    public static void main(String[] args) {
        for (int i = 0; i < 1000; i++) {
            System.out.println(generateUniqueKey());
        }
    }
}
