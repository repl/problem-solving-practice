package array;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.lang.Math.abs;
import static java.lang.Math.min;

public class ShortestWordDistance2 {
    public static void main(String[] args) {
        WordDistance wordDistance = new WordDistance(new String[]{"practice", "makes", "perfect", "coding", "makes"});
        System.out.println(wordDistance.shortestWordDistance("coding", "practice"));
        System.out.println(wordDistance.shortestWordDistance("makes", "coding"));
    }

    static class WordDistance {
        Map<String, List<Integer>> cache = new HashMap<>();
        WordDistance(String[] words) {
            for (int i = 0; i < words.length; i++) {
                List<Integer> wordEntry = cache.getOrDefault(words[i], new ArrayList<Integer>());
                wordEntry.add(i);
                cache.put(words[i], wordEntry);
            }
        }
        public int shortestWordDistance(String word1, String word2) {
            List<Integer> word1List = cache.get(word1);
            List<Integer> word2List = cache.get(word2);
            int distance = Integer.MAX_VALUE;
            int i = 0;
            int j = 0;
            while(i<word1List.size() && j<word2List.size()) {
                distance = min(distance, abs(word1List.get(i) - word2List.get(j)));
                if (word1List.get(i) < word2List.get(j)) {
                    i++;
                } else {
                    j++;
                }
            }
            return distance;
        }
    }
}
