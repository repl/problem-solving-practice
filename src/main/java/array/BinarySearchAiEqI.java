package array;

import java.util.*;

/* Find index in a binary search array where value at the index is same as index.*/

public class BinarySearchAiEqI {
    public static void main(String[] args) {

        List<Integer> input = Arrays.asList(-2,-1,0,2,4,6,8);
        System.out.println(bsearchAiEqI(input));
    }

    public static int bsearchAiEqI(List<Integer> list) {
        int L = 0, U = list.size() - 1;
        while (L <= U) {
            int M = (L + U) / 2;
            if (list.get(M) == M) {
                return M;
            } else if (list.get(M) < M) {
                L = M + 1;
            } else {
                U = M - 1;
            }
        }
        return -1;
    }
}
