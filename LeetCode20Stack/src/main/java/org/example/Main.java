package org.example;

import java.util.Stack;

public class Main {
    public static void main(String[] args) {
        System.out.println(isValid("(])"));
    }

    public static boolean isValid(String s) {
        Stack<Character> stack1 = new Stack<>();
        for(Character c: s.toCharArray()){
           //´pushes
           if (c.equals('(')){
               stack1.push('(');
           }
           else if (c.equals('[')){
               stack1.push('[');
           }
           else if (c.equals('{')){
               stack1.push('{');
           }
           //pops
           if (c.equals(')')){
               if (stack1.isEmpty() ) {
                   return false;
               }

               if (stack1.peek().equals('(')) {
                   stack1.pop();
               }
               else {
                   return false;
               }
           }
           else if (c.equals(']')){
               if (stack1.isEmpty()) {
                   return false;
               }

               if (stack1.peek().equals('[')) {
                   stack1.pop();
               }
               else {
                   return false;
               }
           }
           else if (c.equals('}')){
               if (stack1.isEmpty()) {
                   return false;
               }

               if (stack1.peek().equals('{')) {
                   stack1.pop();
               }
               else {
                   return false;
               }
           }
        }
        return stack1.isEmpty();
    }
}