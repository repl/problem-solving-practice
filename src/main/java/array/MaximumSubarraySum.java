package array;

import java.util.Arrays;
import java.util.List;

public class MaximumSubarraySum {
    /*
     * The maximum subarray problem is a task to find the series of contiguous
     * elements with the maximum sum in any given array.
     * 
     * For instance, in the below array, the highlighted subarray has the maximum
     * sum(6):
     -3 1 -8 4 -1 2 1 -5 5
             |      |
     */
    public static void main(String[] args) {
        System.out.println("Max sum: " + compute(Arrays.asList(-3, 1, -8, 4, -1, 2, 1, -5, 5)));
    }

    //Kadane Algorithm
    public static int compute(List<Integer> input) {
        int maxSoFar = input.get(0);
        int maxEndHere = maxSoFar;
        int start = 0, end = 0;
        for (int i = 1;i < input.size(); i++) {
            if (input.get(i) > maxEndHere + input.get(i)) {
                maxEndHere = input.get(i);
                start = i;
            } else {
                maxEndHere = maxEndHere + input.get(i);
            }
            if (maxEndHere > maxSoFar) {
                maxSoFar = maxEndHere;
                end = i;
            }
        }
        System.out.printf("Max sum sub array found with indices %d, %d\n", start, end);
        return maxSoFar;
    }
}
