package array;

import java.util.*;

public class ThreeSumClosest {
    /*
      Given an array S of n integers, find three integers in S such that the sum is closest to a
      given number, target. Return the sum of the three integers. You may assume that each
      input would have exactly one solution.
    
      For example, given array S = {-1 2 1 -4}, and target = 1.
      The sum that is closest to the target is 2. (-1 + 2 + 1 = 2).
     */
    public static void main(String[] args) {
        System.out.println ( threeSum(new int[]{ -1, 0, 1, 2, -1, -4}, 4));
    }

    private static int threeSum(int[] input, int target) {
        Arrays.sort(input);
        int min = Integer.MAX_VALUE;
        int result = 0;
        
        for(int i=0;i<input.length-2;i++) {
            int start = i+1;
            int end = input.length-1;
            while (start < end) {
                int sum = input[i] + input[start] + input[end];
                int diff = Math.abs(sum - target);
                if (diff == 0) {
                    return sum;
                }
                if (diff < min) {
                    min = diff;
                    result = sum;
                }
                if (sum < target) {
                    start++;
                } else {
                    end--;
                }
            }
        }
        return result;
    }
}
