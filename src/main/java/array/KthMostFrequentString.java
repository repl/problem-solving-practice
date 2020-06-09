package array;

import java.util.*;
import java.util.stream.Collectors;

public class KthMostFrequentString {
    public static void main(String[] args) {
        int k = 0;
        List<String> input = Arrays.asList("a", "b", "c", "a", "b", "a");

        System.out.println(String.format("%dth frequent string: %s", k, getResult(input, k)));
    }

    public static String getResult(List<String> input, int k) {
        Map<String, Integer> countMap = new HashMap<>();
        for (String value : input) {
            Integer count = countMap.getOrDefault(value, null);
            if (count == null) {
                count = 0;
            }
            countMap.put(value, ++count);
        }
        List<Map.Entry<String, Integer>> countList = countMap.entrySet().stream().collect(Collectors.toList());
        Collections.sort(countList, new Comparator<Map.Entry<String, Integer>>() {
                @Override
                public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                    if (o1.getValue() > o2.getValue()) {
                        return -1;
                    } else if (o1.getValue() < o2.getValue()) {
                        return 1;
                    }
                    return 0;
                }
            });
        return countList.get(k).getKey();
    }
}
