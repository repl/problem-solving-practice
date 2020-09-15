package array;

import java.util.Arrays;
import java.util.List;

public class JumpOverNumbers {
    public static void main(String[] args) {
        List<Integer> input = Arrays.asList(3, 4, 1, 2, 5, 6, 9, 0, 1, 2, 3, 1);
        System.out.println(compute(input));
    }

    public static int compute(List<Integer> input) {
        int idx = 0, jumps = 0;
        while (idx < input.size()) {
            int val = input.get(idx);
            if (val == 0) {
                jumps = -1;
                break;
            } else {
                idx += val;
                jumps++;
            }
        }
        return jumps;
    }
}
