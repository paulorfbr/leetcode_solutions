package org.example;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class Main {
    public static void main(String[] args) {

        int n = 15;
        Function<Integer, Integer> fibonacciMemoized = memoize(Main::fibonacci);

        for (int i = 0; i <= n; i++) {
            System.out.println("Fibonacci number at index " + i + ": " + fibonacciMemoized.apply(i));
        }
    }

    public static <T, R> Function<T, R> memoize(Function<T, R> function) {
        Map<T, R> cache = new HashMap<>();
        return input -> cache.computeIfAbsent(input, function);
    }
}