package stack;

import edu.princeton.cs.algs4.Stack;

public class LongestValidParenthesis {
  public static void main(String[] args) {
    System.out.println("Result: " + compute("(()"));
    System.out.println("Result: " + compute(")()()("));
  }

  public static int compute(String a) {
    Stack<Character> stack = new Stack<>();
    int length = 0, maxLength = 0;
    for(int i=0;i<a.length();i++) {
      char c = a.charAt(i);
      if (c == '(') {
        stack.push(c);
      } else {
        if (stack.size() != 0) {
          if (stack.pop().equals('(')) {
            length += 2;
          } else {
            maxLength = Math.max(maxLength, length);
            length = 0;
          }
        } else {
          maxLength = Math.max(maxLength, length);
          length = 0;
        }
      }
    }
    return maxLength = Math.max(maxLength, length);
  }
}
