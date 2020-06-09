package array;

public class ZeroMatrix {
    public static void main(String[] args) {
        boolean[][] input = {{true, false, false},
                             {false, false, false},
                { false, false, false } };
        boolean[][] res = compute(input);
        for (int i = 0; i < res.length; i++) {
            System.out.print("{");
            for (int j = 0; j < res[i].length; j++) {
                System.out.print(res[i][j] + ",");
            }
            System.out.println("}");
        }
    }

    public static boolean[][] compute(boolean[][] arr) {
        int[] row = {0, 0, 0};
        int[] col = { 0, 0, 0 };
        boolean[][] res = new boolean[arr.length][arr[0].length];
        for (int i = 0;i< arr.length; i++) {
            for (int j = 0;j <arr[i].length; j++) {
                if (arr[i][j] == true) {
                    res[i][j] = arr[i][j];
                    if (row[i] != 1) {
                        for (int k = 0; k < arr[i].length;k++) {
                            res[i][k] = true;
                        }
                        row[i] = 1;
                    }
                    if (col[j] != 1) {
                        for (int k = 0; k < arr.length; k++) {
                            res[k][j] = true;
                        }
                        row[j] = 1;
                    }
                }
            }
        }
        return res;
    }
}
