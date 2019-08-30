package array;

import java.util.*;
import java.util.stream.Collectors;

public class KthMostFrequentString {
    public static void main(String[] args) {
        Map<String, Integer> countMap = new HashMap<>();
        List<String> input = Arrays.asList("a", "b", "c", "a", "b", "a");
        int k = 0;
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
        System.out.println(countList.get(k).getKey());
    }
}
