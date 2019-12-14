package array;

public class TrappingRainWater {
    /*
      Given n non-negative integers representing an elevation map where the width of each
      bar is 1, compute how much water it is able to trap after raining.
      For example, given [0,1,0,2,1,0,1,3,2,1,2,1], return 6.
     */    
    public static void main(String[] args) {
        System.out.println(compute(new int[]{1,2,4,2,3,2,5,2}));
    }

    public static int compute(int[] arr) {
        if (arr == null || arr.length < 2) {
            return 0;
        }
        //TODO 
        return 0;
    }
}
