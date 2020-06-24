package array;

import java.util.Arrays;
import java.util.List;

public class PartitionOnPivot {
    public static void main(String[] args) {
        List<Integer> array = Arrays.asList(0,1,2,0,2,1,1);
        System.out.println(compute(array, 3));
    }

    public static List<Integer> compute(List<Integer> input, int pivot) {
        int j = input.size() - 1, n = 0;
        int pv = input.get(pivot);
        while (n < j) {
            if (input.get(n) > pv) {
                int temp = input.get(n);
                input.set(n, input.get(j));
                input.set(j, temp);
                j--;
            } else {
                n++;
            }
        }
        return input;
    }
}
