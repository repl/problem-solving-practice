package dynamicprogramming;

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

        System.out.println("Recursive: " + recursive(arr));

        System.out.println("Recursive DP: " + recursiveDP(arr));

        System.out.println("Iterative DP: " + iterativeDP(arr));
    }

    private static int recursive(boolean arr[][]) {
        int max = 0;
        for (int i = 0;i<arr.length;i++) {
            for (int j = 0;j<arr[0].length;j++){
                if (arr[i][j]) {
                    max = Math.max(max, recursive(arr, i, j));
                }
            }
        }
        return max;
    }

    private static int recursive(boolean[][] arr, int tx, int ty) {
        if (tx == arr.length || ty == arr[0].length) return 0;
        if (!arr[tx][ty]) return 0;

        return 1 + Math.min(recursive(arr, tx + 1, ty),
                          Math.min(recursive(arr, tx + 1, ty + 1), recursive(arr, tx, ty + 1)));
    }

    private static int recursiveDP(boolean[][] arr) {
        int[][] cache = new int[arr.length][arr[0].length];
        int max = 0;
        for (int i = 0; i < arr.length;i++) {
            for (int j = 0;j< arr[0].length;j++) {
                max = Math.max(max, recursiveDPI(arr, i, j, cache));
            }
        }
        return max;
    }

    private static int recursiveDPI(boolean[][] arr, int x, int y, int[][] cache) {
        if (x == arr.length || y == arr[0].length)
            return 0;
        if (!arr[x][y])
            return 0;
        int cacheVal = cache[x][y];
        if (cacheVal > 0)
            return cacheVal;
        cache[x][y] = 1 + Math.min(recursiveDPI(arr, x + 1, y, cache),
                                   Math.min(recursiveDPI(arr, x, y + 1, cache), recursiveDPI(arr, x + 1, y + 1, cache)));
        return cache[x][y];
    }

    private static int iterativeDP(boolean arr[][]) {
        int max = 0;
        int[][] cache = new int[arr.length][arr[0].length];
        for (int i=0;i<cache.length;i++) {
            for (int j=0;j<cache[0].length;i++) {
                // If we’re in the first row/column then
                // the value is just 1 if that cell is
                // true and 0 otherwise. In other rows and
                // columns need to look up and to the left
                if (i == 0 || j == 0) {
                    cache[i][j] = arr[i][j] ? 1 : 0;
                } else if (arr[i][j]) {
                    cache[i][j] = Math.min(Math.min(cache[i][j - 1], cache[i - 1][j]), cache[i - 1][j - 1]) + 1;
                }
                if (cache[i][j] > max)
                    max = cache[i][j];
            }
        }
        return max;
    }
}
