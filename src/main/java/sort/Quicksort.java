package sort;

import java.util.Arrays;

public class Quicksort {
    public static void main(String[] args) {
        int inp[] = {10, 80, 30, 90, 40, 50, 70};
        System.out.println(Arrays.toString(sort(inp)));        
    }

    public static int[] sort(int[] inp) {
        sort(inp, 0, inp.length - 1);
        return inp;
    }

    public static void sort(int[] arr, int start, int end) {
        if (start < end) {            
            int partIndex = partition(arr, start, end);
            sort(arr, start, partIndex - 1);
            sort(arr, partIndex + 1, end);            
        }
    }

    public static int partition(int[] arr, int start, int end) {
        int pivot = arr[end];
        int rrider = end - 1;
        for (int i = start; i <= rrider;) {
            if (arr[i] > pivot) {
                int temp = arr[i];
                arr[i] = arr[rrider];
                arr[rrider] = temp;
                rrider--;
            } else {
                i++;
            }
        }
        int temp = arr[rrider + 1];
        arr[rrider + 1] = pivot;
        arr[end] = temp;
        return rrider + 1;
    }
}
