package array;

import java.util.*;

/* Find index in a binary search array where value at the index is same as index.

From: https://www.geeksforgeeks.org/google-interview-experience-for-sde-1/
Example: [ 4, 3, 1, 5, 7, 6, 10]
Ans: 5, 10
Time Complexity: O(n)
Space Complexity: O(n)
*/

public class BinarySearchOutOfOrder {
    public static void main(String[] args) {

        int[] input = {4, 3, 1, 5, 7, 6, 10};
        System.out.println(elementsInOrder(input));
    }

    public static List<Integer> elementsInOrder(int[] input) {
        /*
          Iterate through the list from right side finding the minimum value found on the right side of an element.
          Then iterate from left side fi nd ing the max value found on the left side of an element. 
          If the left side value is greater than the element or the right side value is lesser than the element, then that element is out of order in the array. 
        */
        List<Integer> ret =  new ArrayList<>();
        int[] rTracker = new int[input.length];
        for (int i = input.length - 1; i>=0; i--) {
            if (i == input.length -1) {
                rTracker[i] = Integer.MAX_VALUE;
            } else {
                rTracker[i] = (input[i + 1] < rTracker[i + 1])? input[i + 1]: rTracker[i + 1];                    
            }
        }
        int[] lTracker = new int[input.length];
        for (int i = 0; i <input.length; i++) {
            if (i == 0) {
                lTracker[i] = Integer.MIN_VALUE;
            } else {
                lTracker[i] = (input[i - 1] > lTracker[i - 1])? input[i - 1]: lTracker[i - 1]; 
            }                        
            if (lTracker[i] < input[i] && rTracker[i] > input[i]) {
                ret.add(input[i]);
            }
        }
        //System.out.println(Arrays.asList);
        return ret;
    }
}
