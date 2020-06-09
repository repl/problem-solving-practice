package array;

public class FindDuplicates {
    public static void main(String[] args) {
        int input[] = {2,1,2,1};
        System.out.println(compute(input));
    }

    public static int compute(int[] arr) {
        int result = 0;
        for (int i=0;i<arr.length;i++) {
            int index = Math.abs(arr[i]);
            if (arr[index] < 0) {
                System.out.println(index);
                result++;
            } else {
                arr[index] = -arr[index];
            }
        }
        return result;
    }
}
