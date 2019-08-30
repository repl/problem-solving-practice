package dynamicprogramming;

public class SubsetSum {
    /*
    https://www.geeksforgeeks.org/subset-sum-problem-dp-25/
    https://stackoverflow.com/questions/4355955/subset-sum-algorithm

    Given a set of non-negative integers, and a value sum, determine if there is a subset of the given set with sum equal to given sum.
    Example:
    Input:  set[] = {3, 34, 4, 12, 5, 2}, sum = 9
    Output:  True  //There is a subset (4, 5) with sum 9.

    Input2: {5, 3, 11, 8, 2}, sum = 16
    Output: True //subset is {5,11}
     */
    public static void main(String[] args) {
        System.out.println(subsetSum(new int[]{3, 34, 4, 12, 5, 2}, 9));
    }

    private static boolean subsetSum(int[] nums, int sum) {
        return recursive(nums, sum, 0);
    }

    private static boolean recursive(int[] nums, int sum, int index) {
        if (index < nums.length) {
            if (sum - nums[index] == 0) {
                return true;
            }
            return (sum - nums[index] > 0 && recursive(nums, sum - nums[index], index+1)
                    || recursive(nums, sum, index+1));
        }
        return false;
    }

}
