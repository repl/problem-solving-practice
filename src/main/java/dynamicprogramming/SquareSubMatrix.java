package dynamicprogramming;

import static java.lang.Math.min;

public class SquareSubMatrix {
    /*
    Given a 2D boolean array, fnd the largest square subarray of
    true values. The return value should be the side length of the
    largest square subarray subarray.
    eq: arr =
    [ F | T | F | F ]
    [ T | T | T | T ]
    [ F | T | T | F ]
    squareSubmatrix(arr) = 2
     */
    public static void main(String[] args) {
        boolean arr[][] = {
                {false, true, false, false},
                {true,  true, true,  true},
                {false, true, true,  false}};
        System.out.println("Recursive: " + recursive(arr, 0, 0));

        System.out.println("Recursive DP: " + recursiveDP());

        System.out.println("Iterative DP: " + iterativeDP());
    }

    private static int recursive(boolean[][] arr, int tx, int ty) {
        if (tx >= arr.length - 1|| ty >= arr[0].length - 1) return 0;
        int answer = 0;
        if (arr[tx][ty]) {
            int subMatrices = min(recursive(arr, tx + 1, ty),
                    min(recursive(arr, tx + 1, ty + 1), recursive(arr, tx, ty + 1)));
            if (subMatrices > 0) {
                answer++;
            }
        }
        return answer;
    }

    private static int recursiveDP() {
        return 0;
    }

    private static int iterativeDP() {
        return 0;
    }
}
