package org.example;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        System.out.println(topKFrequent(new int[]{1,1,1,2,2,3}, 2));


        {
    }

    public static List topKFrequent(int[] nums, int k) {
        Map<Integer, Long> countFreq = Arrays.stream(nums)
                .boxed() // box the ints to Integers
                .collect(Collectors.groupingBy(Function.identity(), // group by the number itself
                        Collectors.counting())); // count the frequency
        //max heap to store the top k
        Queue<Map.Entry<Integer, Long>> maxHeap = new PriorityQueue<>(Collections.reverseOrder(Comparator.comparingLong(Map.Entry::getValue)));
        for (Map.Entry<Integer, Long> entry : countFreq.entrySet()) {
            maxHeap.offer(entry);
        }

        //extract top k
        List topK = new ArrayList(k);
        for (int i=0;i<k;i++) {
            Map.Entry<Integer, Long> maxItem = maxHeap.poll();
            topK.add(maxItem.getKey());
        }
        return topK;
    }
}