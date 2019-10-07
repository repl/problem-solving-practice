package array;

import java.util.Comparator;
import java.util.*;

public class ConvertToBiggestNum {
    /* Write a function that takes a number as input and outputs the biggest number with the same set of digits. */

    public static void main(String[] args) {
        int input = 423865;
        System.out.println(convert(input));
    }

    public static int convert(int n) {
        String strN = String.valueOf(n);
        char[] arrN = strN.toCharArray();
        Arrays.sort(arrN);
        StringBuilder strBuilder = new StringBuilder();
        for (int i = arrN.length - 1; i >= 0; i--) {
            strBuilder.append(arrN[i]);
        }
        return Integer.parseInt(strBuilder.toString());
    }
}
