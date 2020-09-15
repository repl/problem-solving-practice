package array;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class KLargestElements {
    public static void main(String[] args) {
        int k = 3;
        int[] input = { 1, 23, 12, 9, 30, 2, 50};
        System.out.println(compute(input, k));
        System.out.println(compute2(input, k));
    }

    private static List<Integer> compute(int[] nums, int k) {
      //Max Queue. 
        PriorityQueue<Integer> pQueue = new PriorityQueue<>(new Comparator<Integer>() {

           @Override
           public int compare(Integer o1, Integer o2) {
               if (o1 > o2) {
                   return -1;
               } else if (o1 < o2) {
                   return 1;
               }
               return 0;
           }
        });
        for (int i: nums) {
            pQueue.add(i);
        }
        return IntStream.range(1, k+1).map(i -> pQueue.poll()).boxed().collect(Collectors.toList());
    }

  private static List<Integer> compute2(int[] nums, int k) {
    PriorityQueue<Integer> pq = new PriorityQueue<>(k);
    for (int i = 0; i< nums.length; i++) {
      pq.add(nums[i]);
      if (pq.size() > k) {
        pq.poll();
      }
    }
    return new ArrayList<Integer>(pq);
  }
}
