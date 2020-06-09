package array;

public class MatrixSearch {
    /*
     * Given an n x m array where all rows and columns are in sorted order, write a
     * function to determine whether the array contains an element x.

     contains([[1,2,3,4],
               [5,6,7,8],
               [9,10,11,12]],
               11) = true
     */
    public static void main(String[] args) {
        int key = 13;
        int[][] arr = { { 1, 2, 3, 4 }, { 5, 6, 7, 8 }, { 9, 10, 11, 12 } };
        System.out.println(compute(arr, 0, 0, arr.length - 1, arr[0].length - 1, key));
    }

    public static boolean compute(int[][] arr, int topx, int topy, int bottomx, int bottomy, int key) {
        int maxX = arr.length - 1;
        int maxY = arr[0].length - 1;
        if (topx < 0 || topx > maxX) return false;
        if (topy < 0 || topy > maxY) return false;
        if (bottomx < 0 || bottomx > maxX) return false;
        if (bottomy < 0 || bottomy > maxY) return false;

        int midx = topx + (bottomx - topx) / 2;
        int midy = topy + (bottomy - topy) / 2;
        if (arr[midx][midy] == key) {
            return true;
        } else if (arr[midx][midy] < key) {
            if (key <= arr[midx][maxY]) {
                for (int i = midy; i <= maxY; i++) {
                    if (key == arr[midx][i])
                        return true;
                }
                return false;
            } else {
                return compute(arr, midx + 1, 0, bottomx, bottomy, key);
            }
        } else {
            if (key >= arr[midx][0]) {
                for (int i = 0; i < midy; i++) {
                    if (key == arr[midx][i])
                        return true;
                }
                return false;
            } else {
                return compute(arr, topx, topy, midx - 1, maxY, key);
            }
        }
    }
}
