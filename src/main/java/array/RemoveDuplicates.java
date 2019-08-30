package array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class RemoveDuplicates {
    public static void main(String[] args) {
        for (int i : removeDuplicatesRecursive(new int[]{1})) {
            System.out.print(i + " ");
        }
        System.out.println("");
        for (int i : removeDuplicatesRecursive(new int[]{1,2,3})) {
            System.out.print(i + " ");
        }
        System.out.println("");
        for (int i : removeDuplicatesRecursive(new int[]{1,1,2,3})) {
            System.out.print(i + " ");
        }
        System.out.println("");
        for (int i : removeDuplicatesRecursive(new int[]{1,2,2,3,3})) {
            System.out.print(i + " ");
        }
        System.out.println("");
        for (int i : removeDuplicatesRecursive(new int[]{1,1,2,3,3})) {
            System.out.print(i + " ");
        }

        System.out.println("Remove Duplicates inplace:");
        for (int i : removeDuplicatesInplace(new int[] { 1, 1, 2, 3, 3 })) {
            System.out.print(i + " ");
        }
    }

    private static Integer[] removeDuplicates(int[] input) {
        List<Integer> filteredInts = new ArrayList<>();
        filteredInts.add(input[0]);
        int trailIndex = 0;
        for (int i=1; i < input.length; i++) {
            if (input[i] != input[trailIndex]) {
                trailIndex = i;
                filteredInts.add(input[i]);
            }
        }
        return filteredInts.toArray(new Integer[1]);
    }

    private static int[] removeDuplicatesInplace(int[] input) {
        int trail = 0;
        int index = 0;
        while (index < input.length) {
            if (input[trail] != input[index]) {
                input[++trail] = input[index];
            }
            index++;
        }
        return Arrays.copyOf(input, trail + 1);
    }

    private static Integer[] removeDuplicatesRecursive(int[] input) {
        List<Integer> inputList = Arrays.stream(input).boxed().collect(Collectors.toList());
        return removeDuplicatesRecursiveInternal(inputList, null).toArray(new Integer[]{});
    }

    private static List<Integer> removeDuplicatesRecursiveInternal(List<Integer> inputList, Integer prevDistinct) {
        if (inputList.size() == 0) {
            return inputList;
        }
        List<Integer> ret = new ArrayList<>();
        if (!inputList.get(0).equals(prevDistinct)) {
            ret.add(inputList.get(0));
            prevDistinct = inputList.get(0);
        }
        if (inputList.size() > 1) {
            ret.addAll(removeDuplicatesRecursiveInternal(inputList.subList(1, inputList.size()), prevDistinct));
        }
        return ret;
    }
}
