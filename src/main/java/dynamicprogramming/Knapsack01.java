package dynamicprogramming;

import java.util.*;

class Knapsack01 {
    static class Item {
        int value;
        int weight;

        public Item(int value, int weight) {
            this.value = value;
            this.weight = weight;
        }
    }

    public static void main(String[] args) {
        Item[] items = new Item[10];
        items[0] = new Knapsack01.Item(6, 2);
        items[1] = new Knapsack01.Item(10, 2);
        items[2] = new Knapsack01.Item(12, 3);

        int maxweight = 5;
        Knapsack01 instance = new Knapsack01();
        System.out.println("Knapsack naive: " + instance.naive(items, 0, maxweight));

        System.out.println("Knapsack recursiveDP: " + instance.recursiveDP(items, 0, maxweight));

    }

    public int naive(Item[] items, int index, int remainingweight) {
        if (index == items.length || items[index] == null) {
            return 0;
        }
        if (items[index].weight > remainingweight) {
            return naive(items, index + 1, remainingweight);
        }
        return Math.max(naive(items, index + 1, remainingweight - items[index].weight) + items[index].value,
                naive(items, index + 1, remainingweight));
    }

    Map<Integer, Map<Integer, Integer>> cache = new HashMap<>();

    // top-down recursive approach
    public int recursiveDP(Item[] items, int index, int remainingweight) {
        return recursiveDP(items, index, remainingweight, cache);
    }

    public int recursiveDP(Item[] items, int index, int remainingweight, Map<Integer, Map<Integer, Integer>> cache) {

        if (index == items.length || items[index] == null) {
            return 0;
        }

        if (!cache.containsKey(index)) {
            cache.put(index, new HashMap<Integer, Integer>());
        }
        Map<Integer, Integer> cacheEntry = cache.get(index);
        Integer cachedValueForWeight = cacheEntry.get(remainingweight);
        if (cachedValueForWeight != null)
            return cachedValueForWeight;

        int toReturn = 0;
        if (items[index].weight > remainingweight) {
            toReturn = naive(items, index + 1, remainingweight);
        } else {
            toReturn = Math.max(naive(items, index + 1, remainingweight - items[index].weight) + items[index].value,
                    naive(items, index + 1, remainingweight));
        }
        cacheEntry.put(remainingweight, toReturn);
        return toReturn;

    }
}
