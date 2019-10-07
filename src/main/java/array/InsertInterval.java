package array;

import java.util.List;
import java.util.ArrayList;

public class InsertInterval {
    /*
      LeetCode 57. Insert Interval    
      Given a set of non-overlapping intervals, insert a new interval into the intervals (merge if necessary).
    
      You may assume that the intervals were initially sorted according to their start times.
    
      Example 1:
    
      Input: intervals = [[1,3],[6,9]], newInterval = [2,5]
      Output: [[1,5],[6,9]]
    
      Example 2:
    
      Input: intervals = [[1,2],[3,5],[6,7],[8,10],[12,16]], newInterval = [4,8]
      Output: [[1,2],[3,10],[12,16]]
      Explanation: Because the new interval [4,8] overlaps with [3,5],[6,7],[8,10].    
     */
    public static class Tuple {
        int x, y;
        public Tuple(int x, int y) {
            this.x = x;
            this.y = y;
        }
        public String toString() {
            return "[" + x + "," + y + "]";
        }
    }
    public static void main(String[] args) {
        int[][] intervals = { { 1, 2 }, { 3, 5 }, { 6, 7 }, { 8, 10 }, { 12, 16 } };
        int[] newInterval = {4,8};

        InsertInterval instance = new InsertInterval();
        instance.insert(intervals, newInterval);
    }

    public int[][] insert(int[][] intervals, int[] newInterval) {
        List<Tuple> result = new ArrayList<>();
        boolean started = false;
        boolean ended = false;
        int startVal = 0;
        for (int i = 0;i<intervals.length;i++) {
            int[] next = intervals[i];
            if (newInterval[0] >= next[0] && newInterval[0] <= next[1]) {
                if (newInterval[1] <= next[1]) {
                    result.add(new Tuple(next[0], next[1]));
                } else {
                    //net interval starts at this interval and continues
                    started = true;
                    startVal = next[0];
                }
            } else if (newInterval[0] < next[0]) {
                if (started && newInterval[1] <= next[1]) {                    
                    result.add(new Tuple(startVal, next[1]));
                    ended = true;
                }
            } else if (ended) {
                result.add(new Tuple(next[0], next[1]));
            }
        }
        System.out.println(result);
        return null;
    }    
}
