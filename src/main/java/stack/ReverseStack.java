package stack;

import edu.princeton.cs.algs4.Stack;

public class ReverseStack {
    public static void main(String[] args) {
        Stack<Integer> stack = new Stack<>();
        stack.push(3);
        stack.push(2);
        stack.push(1);
        System.out.println("Stack before reversal: " + stack.toString());
        Stack reversedStack = reverse(stack);
        System.out.println("Stack after reversal: " + reversedStack.toString());
    }

    private static Stack reverse(Stack<Integer> stack) {
        if (stack.isEmpty()) {
            return stack;
        }
        Integer topElement = stack.pop();
        Stack reversedStack = reverse(stack);
        insertAsBottomElement(reversedStack, topElement);
        return reversedStack;
    }

    private static void insertAsBottomElement(Stack<Integer> stack, Integer topElement) {
        if (stack.isEmpty()) {
            stack.push(topElement);
        } else {
            Integer temp = stack.pop();
            insertAsBottomElement(stack, topElement);
            stack.push(temp);
        }
    }
}
