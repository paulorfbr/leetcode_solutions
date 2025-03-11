package org.example;

public class Main {
    public static void main(String[] args) {
        System.out.println(strStr("sadbutsad", "sad"));
        System.out.println(strStr( "leetcode", "leeto"));
        System.out.println(strStr("mississippi", "issip"));
        System.out.println(strStr( "a",  "a"));

    }

    public static int strStr(String haystack, String needle) {
       return strStrAux(haystack, needle);
    }

    public static int strStrAux(String haystack, String needle) {
        for (int i=0; i<haystack.length();i++){
            if (i+needle.length()<=haystack.length()) {
                String sub = haystack.substring(i, i + needle.length());
                if (sub.equals(needle)) {
                    return i;
                }
            }
            else {
                break;
            }
        }
        return -1;
    }
}