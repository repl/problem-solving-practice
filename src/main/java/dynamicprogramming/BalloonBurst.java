package dynamicprogramming;

import java.util.ArrayList;
import java.util.List;

public class BalloonBurst {
    public static void main(String[] args) {
        System.out.println(maxCoinsNaive(new int[]{3,5,8}));
    }

    public static int maxCoinsNaive(int[] nums) {
        List<Integer> balloons = new ArrayList<>();
        for (int i = 0; i < nums.length; i++)
            balloons.add(nums[i]);
        return maxCoinNaiveRec(balloons);
    }

    public static int maxCoinNaiveRec(List<Integer> balloons) {
        int max = 0;
        int listSize = balloons.size();
        int i = 0;
        while(i < listSize) {
            int left = (i == 0) ? 1 : balloons.get(i - 1);
            int right = (i == listSize - 1) ? 1 : balloons.get(i + 1);
            int n = left * balloons.get(i) * right;
            List<Integer> tmp = new ArrayList<>(balloons);
            tmp.remove(i);
            max = Math.max(max, n + maxCoinNaiveRec(tmp));
            i++;
        }
        System.out.println("list:" + balloons + ", max:" + max);
        return max;
    }

}
