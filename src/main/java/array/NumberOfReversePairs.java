package array;

import java.util.Arrays;

public class NumberOfReversePairs {
  public static void main(String[] args) {
    int[] input = {10, 3, 4, 2, 5, 7, 9, 11};
    System.out.println("Result: " + divide(input, 0, input.length-1));
  }

  public static int divide(int[] input, int low, int high) {
    if (low>=high) {
      return 0;
    }
    int mid = low + (high - low)/2;
    int leftInversions = divide(input, low, mid);
    int rightInversions = divide(input, mid, high);
    int mergeInversions = mergeAndCount(input, low, mid, high);
    return leftInversions + rightInversions + mergeInversions;
  }

  public static int mergeAndCount(int[] input, int low, int mid, int high) {
    int[] left = Arrays.copyOfRange(input, low, mid + 1);
    int[] right = Arrays.copyOfRange(input, mid + 1, high + 1);
    int i = 0, j = 0, k = low, inversions = 0;
    while (i < left.length && j < right.length) {
      if (left[i] <= right[j]) {
        input[k++] = left[i++];
      } else {
        input[k++] = right[j++];
        inversions += (mid + 1) - (low + i);
      }
    }
    while (i<left.length) {
      input[k++] = left[i++];
    }
    while (j<right.length) {
      input[k++] = left[j++];
    }
    return inversions;
  }
}
