package com.receipt.storage;

import java.util.concurrent.ConcurrentHashMap;

public class ReceiptStorage {
    private static final ConcurrentHashMap<String, Integer> storage = new ConcurrentHashMap<>();

    public static void storeReceipt(String id, int points) {
        storage.put(id, points);
    }

    public static Integer getPoints(String id) {
        return storage.get(id);
    }

}
