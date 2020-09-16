package array;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class LongestSubstringWithUniqueKChars {
  public static void main(String[] args) {
    String input = "aabbaacdeeeeddded";
    int k = 3;
    System.out.println(String.format("length of longest substring with unique %d characters is %d",k,
                                     compute(input, k)));
  }

  public static int compute(String input, int k) {
    Set<Character> uniqueChars = new HashSet<>();
    Map<Character, Integer> charEndingIndex = new HashMap<>();
    int start = 0, maxLength= 0;
    for (int i=0;i<input.length();i++) {
      char c = input.charAt(i);
      if (!uniqueChars.contains(c)) {
        if (uniqueChars.size() >= k) {
          maxLength = Math.max(maxLength, i - start);
          int minCharEndingindex = Integer.MAX_VALUE;
          Character charAtMinIndex = null;
          for (Map.Entry<Character, Integer> entry: charEndingIndex.entrySet()) {
            if (entry.getValue() < minCharEndingindex) {
              minCharEndingindex = entry.getValue();
              charAtMinIndex = entry.getKey();
            }
          }
          uniqueChars.remove(charAtMinIndex);
          charEndingIndex.remove(charAtMinIndex);
          start = minCharEndingindex + 1;
        }
      }
      uniqueChars.add(c);
      charEndingIndex.put(c, i);
    }
    maxLength = Math.max(maxLength, input.length() - start);
    return maxLength;
  }
}
