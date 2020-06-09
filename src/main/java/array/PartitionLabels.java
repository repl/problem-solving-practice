package array;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Interval implements Comparable<Interval> {
    int s, e;

    public Interval() {
        this.s = 0;
        this.e = 0;
    }

    public Interval(int s, int e) {
        this.s = s;
        this.e = e;
    }

	@Override
	public int compareTo(Interval o) {
		return this.s - o.s;
	}

	@Override
	public String toString() {
		return "(" + s + "," + e + ")";
	}
}

public class PartitionLabels {
    public static void main(String[] args) {
        String input = "ababfeefhijkh";
        System.out.println(partitionLabels(input));
    }

    public static List<Integer> partitionLabels(String s) {
        //find intervals for each character
        Map<Character, Interval> charInterval = new HashMap<>();
        for (int i = 0;i<s.length();i++) {
            char c = s.charAt(i);
            Interval interval = charInterval.getOrDefault(c, null);
            if (interval == null) {
                interval = new Interval(i, i);
                charInterval.put(c, interval);
            }
            interval.e = i;
        }
        //merge the intervals
        List<Interval> intervals = new ArrayList<>(charInterval.values());
        Collections.sort(intervals);
        System.out.println("Intervals: " + intervals);
        List<Interval> mergedIntervals = new ArrayList<>();
        List<Integer> partitions = new ArrayList<>();
        Interval nextInterval = new Interval();
        for (Interval interval: intervals) {
            if (nextInterval == null) {
                nextInterval = interval;
                continue;
            }
            if (interval.s > nextInterval.e) {
                mergedIntervals.add(nextInterval);
                partitions.add(nextInterval.e - nextInterval.s + 1);
                nextInterval = new Interval(interval.s, interval.e);
            } else if (interval.e > nextInterval.e) {
                nextInterval.e = interval.e;
            }
        }
        if (nextInterval != null) {
            mergedIntervals.add(nextInterval);
            partitions.add(nextInterval.e - nextInterval.s + 1);
        }
        System.out.println("Merged intervals: " + mergedIntervals);
        System.out.println("Partitions: " + partitions);
        return partitions;
    }
}
