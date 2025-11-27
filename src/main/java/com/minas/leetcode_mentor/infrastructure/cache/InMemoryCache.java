package com.minas.leetcode_mentor.infrastructure.cache;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class InMemoryCache<T> {
    private Map<String, CachedValue<T>> store = new ConcurrentHashMap<>();
    private long ttlMillis = 1200000;

    public void put(String key, T value) {
        store.put(key, new CachedValue<>(value,System.currentTimeMillis()));
    }

    public T get(String key) {
        CachedValue<T> cached = store.get(key);

        if(cached == null) return null;

        // Is expired > Check timestamp with ttlMillis against now
        if(cached.timestamp + ttlMillis < System.currentTimeMillis()) {
            store.remove(key);
            return null;
        }

        return cached.value;
    }

    @AllArgsConstructor
    private static class CachedValue<T> {
        T value;
        long timestamp;
    }
}
