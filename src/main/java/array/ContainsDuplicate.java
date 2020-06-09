package array;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class ContainsDuplicate {
    /*
     * Given an array of integers, find if the array contains any duplicates. Your
     * function should return true if any value appears at least twice in the array,
     * and it should return false if every element is distinct.
     */
    public static void main(String[] args) {
        System.out.println(compute(new int[]{1,2,4,2,3,2,5,2}));
        System.out.println(compute2(new int[] { 1, 2, 4, 2, 3, 2, 5, 2 }));
        System.out.println(compute(new int[] { 1, 1, 3, 2, 4, 1, 4, 2, 2, 3 }));
        System.out.println(compute2(new int[]{1,1,3,2,4,1,4,2,2,3}));
    }

    public static boolean compute(int[] arr) {
        Set<Integer> set = new HashSet<>();
        for (int i=0;i<arr.length;i++) {
            if (!set.contains(arr[i])) {
                set.add(arr[i]);
            } else {
                return true;
            }
        }
        return false;
    }

    public static boolean compute2(int[] arr) {
        Arrays.sort(arr);
        for (int i = 1;i<arr.length;i++) {
            if (arr[i-1] == arr[i]) {
                return true;
            }
        }
        return false;
    }
}
