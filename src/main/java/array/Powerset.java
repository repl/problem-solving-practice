package array;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Powerset {
    public static void main(String[] args) {
        Set<Integer> mySet = new HashSet<Integer>();
        mySet.add(1);
        mySet.add(2);
        mySet.add(3);
        // [1, 2, 3] -> [1], [2], [3], [1, 2], [1, 3], [2, 3], [1, 2, 3]
        Set<Set<Integer>> results = powersetIterative(mySet);
        System.out.println("Iterative: ");
        results.forEach(x -> System.out.println(x.toString()));

        results = powersetRecursive(mySet);
        System.out.println("Recursive: ");
        results.forEach(x -> System.out.println(x.toString()));
    }

    private static Set<Set<Integer>> powersetIterative(Set<Integer> input) {
        Set<Set<Integer>> result = new HashSet<Set<Integer>>();
        result.add(new HashSet<>());
        Set<Set<Integer>> temp = new HashSet<Set<Integer>>();
        input.forEach(element -> {
            Set<Integer> x = new HashSet<>();
            x.add(element);
            temp.add(x);
        });
        while (!temp.isEmpty()) {
            Set<Integer> nextElement = temp.iterator().next();
            temp.remove(nextElement);
            if (!result.contains(nextElement)) {
                result.add(nextElement);
            }
            Set<Set<Integer>> nextElementSet = new HashSet<Set<Integer>>();
            temp.forEach(tempElement -> {
                Set<Integer> newElement = tempElement.stream().collect(Collectors.toSet());
                newElement.addAll(nextElement);
                nextElementSet.add(newElement);
            });
            nextElementSet.forEach(newElement -> {
                if (!temp.contains(newElement)) {
                    temp.add(newElement);
                }
            });
        }
        return result;
    }

    private static Set<Set<Integer>> powersetRecursive(Set<Integer> input) {
        Set<Set<Integer>> sets = new HashSet<Set<Integer>>();
        if (input.isEmpty()) {
            sets.add(new HashSet<Integer>());
            return sets;
        }
        Integer nextElement = input.iterator().next();
        input.remove(nextElement);
        for (Set<Integer> set : powersetRecursive(input)) {
            Set<Integer> newSet = new HashSet<Integer>();
            newSet.add(nextElement);
            newSet.addAll(set);
            sets.add(newSet);
            sets.add(set);
        }
        return sets;
    }
}
