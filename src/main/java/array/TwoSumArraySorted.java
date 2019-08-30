package array;

import java.util.Map;

class TwoSumArraySorted {
    public static void main(String[] args) {
        int[] inp = {2, 3, 5, 8, 11, 20};
        Map.Entry<Integer, Integer> result = twoSum(inp, 16);        
        System.out.println(result == null? "No matching pair." : result.getKey() + "," + result.getValue());
    }

    static Map.Entry<Integer,Integer> twoSum(final int[] inp, int target) {
        int i = 0, j = inp.length - 1;
        while (i < j) {
            int sum = inp[i] + inp[j];
            if (sum < target) {
                i++;
            } else if (sum > target) {
                j--;
            } else {
                final int x = i, y = j;
                return new Map.Entry<Integer,Integer>() {
                    public Integer getKey() {
                        return inp[x];
                    }
                    public Integer getValue() {
                        return inp[y];
                    }
                    public Integer setValue(Integer v) {
                        return v;
                    }
                };
            }
        }
        return null;
    }
}
