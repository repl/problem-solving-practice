package array;

public class ContainerWithMostWater {
    /*
      Given n non-negative integers a1, a2, ..., an, where each represents a point at coordinate
      (i, ai). n vertical lines are drawn such that the two endpoints of line i is at (i, ai)
      and (i, 0). Find two lines, which together with x-axis forms a container, such that the
      container contains the most water.

      Initially we can assume the result is 0. Then we scan from both sides. 
      If leftHeight < rightHeight, move right and find a value that is greater than leftHeight. 
      if leftHeight > rightHeight, move left and find a value that is greater than rightHeight.
      Additionally, keep tracking the max value.
     */    
    public static void main(String[] args) {
        System.out.println(compute(new int[]{1,2,4,2,3,2,5,2}));
        System.out.println(compute(new int[]{1,1,3,2,4,1,4,2,2,3}));
    }

    public static int compute(int[] arr) {
        if (arr == null || arr.length < 2) {
            return 0;
        }
        int max = 0;
        int left = 0;
        int right = arr.length - 1;
        while (left < right) {
            max = Math.max(max, (right - left) * Math.min(arr[left], arr[right]));
            if (arr[left] < arr[right]) {
                left++;
            } else if (arr[left] > arr[right]) {
                right--;
            } else {
                left++;
                right--;
            }
        }
        return max;
    }
}
