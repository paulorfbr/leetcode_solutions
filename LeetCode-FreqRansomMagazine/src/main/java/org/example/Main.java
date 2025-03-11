package org.example;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        System.out.println(canConstruct("a",  "b"));
        System.out.println(canConstruct("aa",  "ab"));
        System.out.println(canConstruct("aa", "aab"));
    }

    public static boolean canConstruct(String ransomNote, String magazine) {
        //count freq of chars in magazine
        Map<Character, Long> charFrequencyAtMagazine = magazine.chars()
                .mapToObj(c -> (char)c).collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        //count freq of chars in ransom note
        Map<Character, Long> charFrequencyAtRansomNote = ransomNote.chars()
                .mapToObj(c -> (char)c)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        for (Map.Entry<Character, Long> freqAtRansom: charFrequencyAtRansomNote.entrySet()){
            //check if each item in ransom note has enough chars at magazine
            if (charFrequencyAtMagazine.containsKey(freqAtRansom.getKey())) {
                Long freqAtMagazine = charFrequencyAtMagazine.get(freqAtRansom.getKey());
                if (freqAtMagazine < freqAtRansom.getValue()) {
                    return false;
                }
            }
            else {
                return false;
            }
        }
        return true;
    }
}