package linkedlist;

import java.util.Stack;

public class Palindrome {

    public static void main(String[] args) {
        Node n5 = new Node(2);
        Node n4 = new Node(3, n5);
        Node n3 = new Node(5, n4);
        Node n2 = new Node(3, n3);
        Node n1 = new Node(2, n2);

        System.out.println("is palindrome? " + isPalindrome(n1));
    }

    public static String listToString(Node n) {
        StringBuffer buff = new StringBuffer();
        Node temp = n;
        while (temp != null) {
            buff.append(temp.data + ",");
            temp = temp.next;
        }
        return buff.toString();
    }

    public static boolean isPalindrome(Node n) {
        Node slow = n;
        Node fast = n;
        Stack<Integer> stack = new Stack<>();
        stack.push(slow.data);
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (fast != null) {
                stack.push(slow.data);
            }
        }
        if (fast != null) {
            slow = slow.next;
            stack.pop();
        }
        System.out.println(listToString(slow));
        System.out.println(stack.peek());
        while (!stack.isEmpty()) {
            if (!stack.pop().equals(slow.data)) {
                return false;
            }
            slow = slow.next;
        }
        return true;
    }
}
