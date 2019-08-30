package dynamicprogramming;

public class SubsetSum2 {
    /*
    Given an array of integers, nums and a target value T, fnd the
    number of ways that you can add up to T.
    eg.
    nums = {1, 1, 1, 1, 1}
    T = 3
    nums[0] + nums[1] + nums[2]
    nums[0] + nums[1] + nums[3]
    nums[0] + nums[1] + nums[4]
    nums[0] + nums[2] + nums[3]
    nums[0] + nums[2] + nums[4]
    nums[0] + nums[3] + nums[3]
    nums[1] + nums[2] + nums[3]
    nums[1] + nums[2] + nums[4]
    nums[1] + nums[3] + nums[4]
    nums[2] + nums[3] + nums[4]
    subsetSum(nums, T) = 10
     */
    public static void main(String[] args) {
        System.out.println(subsetSum(new int[]{1, 1, 1, 1, 1}, 3));
    }

    private static int subsetSum(int[] input, int sum) {
        int index = 0;
        int addUp = 0;
        while (index < input.length) {
             addUp += subsetSum(input, sum, index);
             index++;
        }
        return addUp;
    }

    private static int subsetSum(int[] input, int sum, int index) {
        if (index < input.length) {
            if (index == input.length - 1 && sum - input[index] == 0) {
                return 1;
            } else {
                int addUp = 0;
                if (sum - input[index] == 0) {
                    addUp += 1;
                } else {
                    int itrIndex = index + 1;
                    while (itrIndex < input.length) {
                        addUp += subsetSum(input, sum - input[index], itrIndex);
                        itrIndex++;
                    }
                }
                return addUp;
            }
        }
        return 0;
    }
}
