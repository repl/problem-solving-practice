package array;

import java.util.*;

public class ThreeSum {
    /*
    Given an array S of n integers, are there elements a, b, c in S such that a + b + c = 0? Find all unique triplets in the
    array which gives the sum of zero.
    Note: Elements in a triplet (a,b,c) must be in non-descending order. (ie, a < b < c) The solution set must not
    contain duplicate triplets.
    For example, given array S = {-1 0 1 2 -1 -4},
    A solution set is:
    (-1, 0, 1)
    (-1, -1, 2)
     */
    public static void main(String[] args) {
        threeSum(new int[]{-1, 0, 1, 2, -1, -4}).forEach(output -> {
            System.out.println(output);
        });
    }

    private static Set<List<Integer>> threeSum(int[] input) {
        Set<List<Integer>> result = new HashSet<>();
        Arrays.sort(input);
        for(int i=0;i<input.length-2;i++) {
            int start = i+1;
            int end = input.length-1;
            while (start < end) {
                int sum = input[i] + input[start] + input[end];
                if (sum == 0) {
                    List<Integer> output = new ArrayList();
                    output.add(input[i]);
                    output.add(input[start]);
                    output.add(input[end]);
                    result.add(output);
                    while(start < end && input[start]==input[start+1]) start++;
                    start++;
                } else if (sum < 0) {
                    start++;
                } else {
                    end--;
                }
            }
        }
        return result;
    }
}
