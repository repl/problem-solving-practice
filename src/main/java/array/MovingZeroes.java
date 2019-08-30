package array;

import java.util.Arrays;

public class MovingZeroes {
    // Given an array nums, write a function to move all 0’s to the end of it while
    // maintaining the relative order of the non-zero elements.
    // For example, given nums=[0,1,0,3,12], after calling your function, nums should be[1,3,12,0,0].

    public static void main(String[] args) {
        int[] inp = {1, 0, 3, 0, 0, 5, 6};
        System.out.println(Arrays.toString(moveZeroes(inp)));
    }

    public static int[] moveZeroes(int[] inp) {
        int i = 0, trail = 0, count = 0;        
        while (i < inp.length) {
            if (inp[i] == 0) {
                count++;
            } else {
                inp[trail] = inp[i];
                trail++;
            }
            i++;
        }
        while (count-- > 0) {
            inp[trail++] = 0;
        }
        return inp;
    }
}
