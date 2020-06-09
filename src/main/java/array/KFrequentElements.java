package array;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Stack;

public class KFrequentElements {
    public static void main(String[] args) {
        int[] nums = {1,2,3,2,1,3,2,4,5,6,5};
        int k = 3;
        System.out.println(String.format("%d frequent elements are %s", k, compute(nums, k)));
    }

    public static List<Integer> compute(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int num: nums) {
            Integer count = map.getOrDefault(num, null);
            if (count == null) {
                count = 0;
            }
            count++;
            map.put(num, count);
        }
        PriorityQueue<Pair> minHeap = new PriorityQueue<>(Comparator.comparing(Pair -> Pair.count));
        for (Map.Entry<Integer, Integer> entry: map.entrySet()) {
            minHeap.add(new Pair(entry.getKey(), entry.getValue()));
            if (minHeap.size() > k) {
                minHeap.poll();
            }
        }
        Stack<Integer> stack = new Stack<>();
        while (minHeap.size() > 0) {
            stack.push(minHeap.poll().num);
        }
        List<Integer> res = new ArrayList<>();
        while (!stack.isEmpty()) {
            res.add(stack.pop());
        }
        return res;
    }
}

class Pair{
    int num;
    int count;
    public Pair(int num, int count) {
        this.num = num;
        this.count = count;
    }
}
