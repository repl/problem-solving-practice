package array;

public class SearchInsertPosition {
    public static void main(String[] args) {

        int[] input = { 1, 3, 5, 6 };
        System.out.println("Result for " + input + " is " + compute(input, 5));
        int[] input2={1,3,5,6};
        System.out.println("Result for " + input2 + " is " + compute(input2, 2));
        int[] input3 = { 1, 3, 5, 6 };
        System.out.println("Result for " + input3 + " is " + compute(input3, 7));
        int[] input4 = { 1, 3, 5, 6 };
        System.out.println("Result for " + input4 + " is " + compute(input4, 0));
    }

    public static int compute(int[] input, int k) {
        return bs(input, k, 0, input.length - 1);
    }

    public static int bs(int[] input, int k, int l, int r) {
        if (l>r) {
            return l;
        }
        if (input[l] == k) {
            return l;
        } else if (input[r] == k) {
            return r;
        } else {
            if (l == r) {
                if (k < input[l]) {
                    return l;
                } else if (k > input[l]) {
                    return l + 1;
                } else {
                    return l;
                }
            } else {
                int m = l + (r - 1) / 2;
                if (input[m] == k) {
                    return m;
                } else if (k < input[m]) {
                    return bs(input, k, l, m - 1);
                } else {
                    return bs(input, k, m + 1, r);
                }
            }
        }
    }
}
