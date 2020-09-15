package dynamicprogramming;

import java.util.ArrayList;
import java.util.List;

public class ZeroSumSubSequence {
  public static void main(String[] args) {
    System.out.println(compute(new int[] { 1, 2, -5, 1, 2, -1 }));
  }

  public static List<Integer> compute(int[] arr) {
    return compute(arr, 0, 0);
  }

  public static List<Integer> compute(int[] arr, int index, int sum) {
    List<Integer> ret = new ArrayList<>();
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

  /*public static List<Integer> iterative(int[] arr) {
    int[][] sum = new int[arr.length][arr.length];
    int s = 0, e = 0;
    for (int i = 0; i < arr.length; i++) {
      sum[i][i] = arr[i];
    }
    for (int i = 0; i < arr.length; i++) {
      for (int j = i + 1; j < arr.length; j++) {
      }
    }
    }*/
}
