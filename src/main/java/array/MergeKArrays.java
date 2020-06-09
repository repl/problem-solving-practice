package array;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class MergeKArrays {
    public static void main(String[] args) {
        Integer[] res = compute(new int[][] {new int[]{1,4,7},new int[]{2,5,8},new int[]{3,6,9}});
        System.out.print("{");
        for (int j = 0; j < res.length; j++) {
            System.out.print(res[j] + ",");
        }
        System.out.println("}");
    }

    public static Integer[] compute(int[][] arrs) {
        List<Integer> res = new ArrayList<>();
        int k = arrs.length;
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        for (int i=0;i<k;i++){
            queue.add(arrs[i][0]);
        }
        boolean hasMore = true;
        int[] kIndexes = new int[k];
        for (int i = 0;i<k;i++) {
            kIndexes[i] = 1;
        }
        int nextKIndex = 0;
        do {
            int kCount = 0;
            res.add(queue.poll());
            while (kIndexes[nextKIndex] >= arrs[nextKIndex].length) {
                nextKIndex++;
                kCount++;
                if (kCount >= k)
                    break;
            }
            if (kCount < k) {
                System.out.println("nextKIndex: " + nextKIndex);
                System.out.println("kCount: " + kCount);
                queue.add(arrs[nextKIndex][kIndexes[nextKIndex]]);
                kIndexes[nextKIndex]++;
                nextKIndex++;
                if (nextKIndex >= k)
                    nextKIndex = 0;
            } else {
                hasMore = false;
            }
        } while(hasMore);
        while (queue.size() > 0) {
            res.add(queue.poll());
        }
        return res.toArray(new Integer[]{});
    }
}
