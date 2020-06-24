package array;

public class GroupEvenOdd {
    public static void main(String[] args) {
        int[] input = {1,2,3,4,5,6,7};
        compute(input);
        System.out.print("[");
        for (int i = 0;i<input.length;i++) {
            System.out.print(input[i]);
        }
        System.out.println("]");
    }

    public static void compute(int[] input) {
        int i = 0, s = 0, e = input.length - 1;
        while (i < e) {
            int temp = input[i];
            if (input[i] % 2 == 0) {
                input[i] = input[s];
                input[s] = temp;
                s++;
            } else {
                input[i] = input[e];
                input[e] = temp;
                e--;
            }
            i++;
        }
    }
}
