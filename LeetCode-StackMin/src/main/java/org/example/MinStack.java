package org.example;

import java.util.Stack;

public class MinStack {
    public static void main(String[] args) {
        MinStack minStack = new MinStack();
        minStack.push(-2);
        minStack.push(0);
        minStack.push(-3);
        System.out.println(minStack.getMin()); // return -3
        minStack.pop();
        System.out.println(minStack.top());    // return 0
        System.out.println(minStack.getMin()); // return -2
    }

    class StackElement {
        int value, currentMinimum;
        StackElement(int value, int currentMinimum) {
            this.value = value;
            this.currentMinimum = currentMinimum;
        }
    }

    private Stack<StackElement> stack;

    public MinStack() {
        this.stack = new Stack<>();
    }

    public void push(int val) {
        int min;
        if (stack.isEmpty()) {
            min = val;
        } else {
            min = Math.min(stack.peek().currentMinimum, val);
        }
        stack.push(new StackElement(val, min));
    }

    public void pop() {
        stack.pop();
    }

    public int top() {
        return stack.peek().value;
    }

    public int getMin() {
        return stack.peek().currentMinimum;
    }


}