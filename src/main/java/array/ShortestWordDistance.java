package array;

import static java.lang.Math.abs;

public class ShortestWordDistance {
    public static void main(String[] args) {
        System.out.println(shortestWordDistance(new String[]{"practice", "makes", "perfect", "coding", "makes"}, "coding", "practice"));
        System.out.println(shortestWordDistance(new String[]{"practice", "makes", "perfect", "coding", "makes"}, "makes", "coding"));
        System.out.println(shortestWordDistance(new String[]{"practice", "makes", "perfect", "coding", "makes"}, "makes", "makes"));
    }

    private static int shortestWordDistance(String[] words, String word1, String word2) {
        int distance = Integer.MAX_VALUE;
        int firstWordIndex = Integer.MAX_VALUE;
        int secondWordIndex = Integer.MAX_VALUE;
        int sameWordTurn = 0;
        if (word1.equals(word2)) {
            sameWordTurn = 1;
        }
        for (int i=0;i<words.length;i++) {
            if (words[i].equals(word1) && (sameWordTurn == 0 || sameWordTurn == 1)) {
                sameWordTurn = 2;
                if (abs(i - secondWordIndex) <= distance) {
                    distance = abs(i - secondWordIndex);
                    firstWordIndex = i;
                }
            } else if (words[i].equals(word2) && (sameWordTurn == 0 || sameWordTurn == 2)) {
                sameWordTurn = 1;
                if (abs(i - firstWordIndex) <= distance) {
                    distance = abs(i - firstWordIndex);
                    secondWordIndex = i;
                }
            }
        }
        return distance;
    }
}
