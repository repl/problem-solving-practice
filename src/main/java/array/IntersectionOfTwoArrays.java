package array;

import java.util.Arrays;
import java.util.HashSet;

public class IntersectionOfTwoArrays {
    public static void main(String[] args) {
        int[] nums1 = { 1, 2, 3, 4 };
        int[] nums2 = { 3, 5, 6, 7 };
        System.out.println(compute(nums1, nums2));
    }

    //Time = O(n), space=O(n)
    public static Integer[] compute(int[] nums1, int[] nums2) {
        HashSet<Integer> set1 = new HashSet<>();
        for (int i: nums1) {
            set1.add(i);
        }
        HashSet<Integer> intersectionSet = new HashSet<>();
        for (int i: nums2) {
            if (set1.contains(i)) {
                intersectionSet.add(i);
            }
        }
        return intersectionSet.toArray(Integer[]::new);
    }

    public static Integer[] compute2(int[] nums1, int[] nums2) {
        Arrays.sort(nums2);
        HashSet<Integer> intersectionSet = new HashSet<>();
        for (int i=0;i<nums1.length;i++) {
            if (Arrays.binarySearch(nums2, nums1[i]) > -1) {
                intersectionSet.add(nums1[i]);
            }
        }
        return intersectionSet.toArray(Integer[]::new);
    }
}
