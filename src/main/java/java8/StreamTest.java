package java8;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class StreamTest {
    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(3, 4, 5);
        System.out.println(list.stream().flatMap(i -> Arrays.asList(i, i).stream()).collect(Collectors.toList()));
    }
}
