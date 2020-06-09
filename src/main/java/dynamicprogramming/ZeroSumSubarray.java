package dynamicprogramming;

import java.util.ArrayList;
import java.util.List;

public class ZeroSumSubarray {
    public static void main(String[] args) {
        System.out.println(compute(new int[] {1, 2, -5, 1,2, -1}));
    }

    public static List<Integer> compute(int[] arr) {
        return compute(arr, 0, 0);
    }

    public static List<Integer> compute(int[] arr, int index, int sum) {
        List<Integer> ret =  new ArrayList<>();
        if (index == arr.length)
            return ret;
        if (sum - arr[index] == 0) {
            ret.add(arr[index]);
            return ret;
        }
        List<Integer> res1 = compute(arr, index + 1, sum - arr[index]);
        if (res1.size() > 0) {
            ret.add(arr[index]);
            ret.addAll(res1);
        }
        List<Integer> res2 = compute(arr, index + 1, sum);
        if (res2.size() > 0) {
            return res2;
        }
        return ret;
    }
}
