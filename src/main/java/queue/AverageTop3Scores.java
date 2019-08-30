package queue;

import java.util.*;

public class AverageTop3Scores {
    public static void main(String[] args) {
        List<Object> scoreList = new ArrayList<>();
        scoreList.add("kapish");
        scoreList.add(99);
        scoreList.add("mayavi");
        scoreList.add(91);
        scoreList.add("kapish");
        scoreList.add(86);
        scoreList.add("mayavi");
        scoreList.add(93);
        scoreList.add("shambhu");
        scoreList.add(97);
        scoreList.add("mayavi");
        scoreList.add(100);
        scoreList.add("kapish");
        scoreList.add(94);
        scoreList.add("shambhu");
        scoreList.add(92);
        scoreList.add("dinkan");
        scoreList.add(100);
        scoreList.add("shambhu");
        scoreList.add(88);
        scoreList.add("mayavi");
        scoreList.add(90);

        System.out.println("Topper: " + findAverageTop3Scorer(scoreList));
    }

    public static String findAverageTop3Scorer(List<Object> scoreList) {
        Map<String, PriorityQueue<Integer>> scoreMap = new HashMap<>();
        Iterator<Object> itr = scoreList.iterator();
        while (itr.hasNext()) {
            String name = (String) itr.next();
            Integer score = (Integer) itr.next();
            PriorityQueue<Integer> scoresQueue = scoreMap.get(name);
            if (scoresQueue == null) {
                scoresQueue = new PriorityQueue<Integer>();
                scoreMap.put(name, scoresQueue);
            }
            scoresQueue.add(score);
            if (scoresQueue.size() > 3) {
                scoresQueue.poll();
            }
        }
        Set<Map.Entry<String, PriorityQueue<Integer>>> mapEntrySet = scoreMap.entrySet();
        Integer topScore = 0;
        String topScorer = "none";
        for (Map.Entry<String, PriorityQueue<Integer>> entry : mapEntrySet) {
            PriorityQueue<Integer> scoresQueue = entry.getValue();
            if (scoresQueue.size() == 3) {
                int individualScore = scoresQueue.stream().reduce(0, (acc, n) -> (acc + n));
                if (topScore < individualScore) {
                    topScore = individualScore;
                    topScorer = entry.getKey();
                }
            }
        }
        return topScorer;
    }
}
