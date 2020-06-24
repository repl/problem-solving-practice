package array;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class IncrementInteger {
    public static void main(String[] args) {
        System.out.println(compute(Stream.of(1,2,3).collect(Collectors.toList())));
        System.out.println(compute(Stream.of(9,9,9).collect(Collectors.toList())));
    }

    public static List<Integer> compute(List<Integer> input) {
        int i = input.size() - 1;
        while (i>=0) {
            int val = input.get(i) + 1;
            if (val == 10) {
                input.set(i, 0);
            } else {
                input.set(i, val);
                break;
            }
            i--;
        }
        if (i == -1) {
            input.set(0, 0);
            input.add(0, 1);
        }
        return input;
    }
}
