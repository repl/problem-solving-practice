package array;

import java.util.Arrays;

public class LongestConsecutiveSequence {
    public static void main(String[] args) {
        int[] input = {4,2,1,6,5};
        System.out.println(compute(input));
        input=new int[] {5,5,3,1};
        System.out.println(compute(input));
    }

    public static int compute(int[] arr) {
        Arrays.sort(arr);
        int result = 1;
        for (int i = 1;i<arr.length;i++) {
            System.out.println("next element: " + arr[i]);
            if (arr[i] - 1 == arr[i - 1]) {
                result++;
            } else {
                result = 1;
            }
        }
        return result;
    }
}
