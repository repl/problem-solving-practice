package array;

import java.util.*;

public class MergeInterval {
    public static void main(String[] args) {
        /*
          Given a collection of intervals, merge all overlapping intervals.

          For example,
          Given [1,3],[2,6],[8,10],[15,18],
          return [1,6],[8,10],[15,18].        
         */
        int[][] input = { { 1, 3 }, { 2, 6 }, { 8, 10 }, { 15, 18 } };
        int[][] output = mergeInterval(input);
        System.out.print("\n[");
        for (int i = 0; i< output.length; i++) {
            if (i > 0) {
                System.out.print(", ");
            }
            System.out.print("[" + output[i][0] + "," + output[i][1] + "]");
        }
        System.out.print("]\n");
    }

    static class Tuple {
        int x;
        int y;
        public Tuple(int x, int y) {
            this.x = x;
            this.y = y;
        }
        public String toString() {
            return "[" + x + "," + y + "]";
        }
    }
    public static int[][] mergeInterval(int[][] input) {
        List<Tuple> collect = new ArrayList<>();
        int l = input[0][0], h = input[0][1];
        for (int i=1;i<input.length;i++) {
            boolean l_mod = false, h_mod = false;
            if (input[i][0] > l && input[i][1] < h) {
                continue;
            } else if (input[i][0] < l && input[i][1] > h) {
                l = input[i][0];
                h = input[i][1];
            } else if (input[i][0] > l && input[i][0] < h && input[i][1] > h) {
                h = input[i][1];
            } else if (input[i][0] < l && input[i][1] > l && input[i][1] < h) {
                l = input[i][0];
            } else {
                collect.add(new Tuple(l, h));
                l = input[i][0];
                h = input[i][1];
            }
        }
        collect.add(new Tuple(l, h));
        final int[][] result = new int[collect.size()][2];
        for (int i = 0; i<collect.size(); i++) {
            result[i][0] = collect.get(i).x;
            result[i][1] = collect.get(i).y;
        }
        return result;
    }
}
