package array;

import java.util.*;

public class BSearch {
    public static void main(String[] args) {
        List<Integer> input = Arrays.asList(2, 3, 5, 8, 9, 12,13 ,14);
        System.out.println(bsearch(input, 3));        
    }

    public static int bsearch(List<Integer> list, int key) {
        int L = 0, U = list.size() - 1;
        while (L <= U) {
            int M = (L + U) / 2;
            if (list.get(M) == key) {
                return M;
            } else if (list.get(M) < key) {
                L = M + 1;
            } else {
                U = M - 1;
            }
        }
        return -1;
    }
}
