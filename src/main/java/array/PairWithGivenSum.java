package array;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Stack;

public class PairWithGivenSum {
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();

        while (t-- > 0) {
            int n = sc.nextInt();
            int arr[] = new int[n];

            for (int i = 0; i < n; i++)
                arr[i] = sc.nextInt();

            int sum = sc.nextInt();

            Stack<String> res = compute(n, arr, sum);
            if (res.empty()) {
                System.out.println("-1");
            } else {
                while (!res.empty()) {
                    System.out.println(res.pop());
                }
            }
        }
    }

    public static Stack<String> compute(int n, int[] arr, int sum) {
        Stack<String> res = new Stack<>();
        int resIndex = 0;
        Map match = new HashMap<Integer, Integer>();
        for (int i = 0; i < n; i++) {
            if (match.containsKey(arr[i])) {
                res.push(match.get(arr[i]) + " " + arr[i] + " " + sum);
            } else {
                match.put(sum - arr[i], arr[i]);
            }
        }
        return res;
    }
}

