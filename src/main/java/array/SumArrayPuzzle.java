package array;

import java.util.*;
import java.io.*;
import java.lang.*;

public class SumArrayPuzzle
{
    /*
    Given an array arr[] of n integers, construct a Sum Array sum[] (of same size) such that sum[i] is equal to the
    sum of all the elements of arr[] except arr[i]. Solve it without subtraction operator and in O(n).
    Input : arr[] = {3, 6, 4, 8, 9}
    Output : sum[] = {27, 24, 26, 22, 21}

    Input : arr[] = {4, 5, 7, 3, 10, 1}
    Output : sum[] = {26, 25, 23, 27, 20, 29}
     */
    public static void main(String args[])
    {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();

        while(t-- > 0)
        {
            int n = sc.nextInt();
            int arr[] = new int[n];

            for(int i = 0; i < n; i++)
                arr[i] = sc.nextInt();

            Geeks obj = new Geeks();
            obj.SumArray(arr, n);
            System.out.println();
        }
    }
}


/*Please note that it's Function problem i.e.
you need to write your solution in the form of Function(s) only.
Driver Code to call/invoke your function is mentioned above.*/

//User function Template for Java
// arr is the array
// n is the number of element in array
class Geeks
{
    public static void SumArray(int arr[], int n)
    {
        int sum = 0;
        for (int i = 0;i < n; i++) {
            sum += arr[i];
        }
        for (int i = 0;i < n; i++) {
            arr[i] = sum - arr[i];
            System.out.print(arr[i]);
            if (i != n - 1) {
                System.out.print(" ");
            }
        }
    }
}