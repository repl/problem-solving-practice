package array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Permutation {
    public static void main(String[] args) {
        System.out.println(compute(Stream.of(1,2,3).collect(Collectors.toList())));
    }

    public static List<List<Integer>> compute(List<Integer> a) {
        return compute(a, 0, a.size() - 1);
    }

    public static List<List<Integer>> compute(List<Integer> a, int s, int e) {
        if (s >= e) {
            List<Integer> newList = new ArrayList<>();
            newList.addAll(a);
            return Arrays.asList(newList);
        }
        List<List<Integer>> res = new ArrayList<>();
        for (int i = s; i <= e; i++) {
            swap(a, s, i);
            res.addAll(compute(a, s + 1, e));
            swap(a, i, s);
        }
        return res;
    }

    public static void swap(List<Integer> a, int x, int y) {
        int temp = a.get(x);
        a.set(x, a.get(y));
        a.set(y, temp);
    }
}
