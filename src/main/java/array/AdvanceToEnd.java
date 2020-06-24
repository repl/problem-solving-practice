package array;

import java.util.Arrays;
import java.util.List;

public class AdvanceToEnd {
    public static void main(String[] args) {
        System.out.println(compute(Arrays.asList(3, 3, 1, 0, 2, 0, 1)));
        System.out.println(compute(Arrays.asList(3, 2, 0, 0, 2, 0, 1)));
        System.out.println(compute(Arrays.asList(2,3,5,5,7,11,11,11,13)));
    }

    public static boolean compute(List<Integer> input) {
        return compute(input, input.size() - 1);
    }

    public static boolean compute(List<Integer> input, int endIndex) {
        if (endIndex == 0) {
            return true;
        }
        for (int i = endIndex - 1; i >= 0; i--) {
            if (input.get(i) >= (endIndex - i)) {
                if (compute(input, i)) {
                    return true;
                }
            }
        }
        return false;
    }
}
