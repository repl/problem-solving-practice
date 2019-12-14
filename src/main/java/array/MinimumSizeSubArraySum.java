package array;

public class MinimumSizeSubArraySum {
    /* Given an array of integers and a integer s, find the length of the minimum subarray whose sum is greater or same as s.

       For example, int array = 2,3,1,2,4,3, and S = 7, result would be 2 (for subarray 4,3)
     */
    public static void main(String args[ ])  { 
         System.out.println( compute(new int[]{2,3,1,2,4,3}, 7) );
    }

    private static int compute(int[] arr, int s) {
        int min = Integer.MAX_VALUE;
        int l = 0, r = 0;
        int sum = arr[l];
        if (sum >= s) {
          min = 1;
          r = 1;
          sum = sum + arr[r];
        }
        do {
            System.out.println("l: " + l + ", r: " + r + ", sum: " + sum);
            if (sum >= s) {
                if (r - l + 1 < min) {
                    min = r - l + 1;
                }
                sum = sum - arr[l];
                l++;
            } else {
               r++;
               if (r < arr.length) {
                  sum = sum + arr[r];
               }
            }
        } while (l < r && r < arr.length);
        return (min == Integer.MAX_VALUE)? 0: min;
    }
}
