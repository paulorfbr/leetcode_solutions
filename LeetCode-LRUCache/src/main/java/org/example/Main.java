package org.example;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
    }


    class LRUCache {
        public class MyLRUCache<K, V> extends LinkedHashMap<K, V> {
            private final int capacity;

            public MyLRUCache(int capacity) {
                super(capacity, 0.75f, true);
                this.capacity = capacity;
            }

            @Override
            protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
                return size() > this.capacity;
            }
        }

        private MyLRUCache<Integer, Integer> myLRUCache;
        public LRUCache(int capacity) {
            this.myLRUCache = new MyLRUCache<>(capacity);
        }

        public int get(int key) {
            return this.myLRUCache.get(key);
        }

        public void put(int key, int value) {
            return this.myLRUCache.put((Integer)key,(Integer)value);
        }
    }
}