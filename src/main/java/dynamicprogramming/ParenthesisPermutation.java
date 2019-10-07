package dynamicprogramming;

import java.util.*;

public class ParenthesisPermutation {

    /*     Print all possible n pairs of balanced parentheses.
    
     For example, when n is 2, the function should print “(())” and “()()”. When n is 3, we should get “((()))”, “(()())”, “(())()”, “()(())”, “()()()”. 
    */
    public static void main(String[] args) {
        int n = 4;
        System.out.println(getPermutation(n));
    }

    private static List<String> getPermutation(int n) {
        List<String> result = new ArrayList<>();
        getPermAux(n, n, "", result);
        return result;
    }
    private static void getPermAux(int left, int right, String combination, List<String> collection) {
        if (left == 0 && right == 0) {            
            collection.add(combination);
        }
        if (left > 0) {
            getPermAux(left - 1, right, combination + "(", collection);
        }
        if (right > left) {
            getPermAux(left, right - 1, combination + ")", collection);
        }
    }
}
