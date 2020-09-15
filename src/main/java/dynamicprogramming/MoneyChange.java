package dynamicprogramming;

public class MoneyChange {
    /*
    Given an integer representing a given amount of change, write a
    function to compute the total number of coins required to make
    that amount of change. You can assume that there is always a
    1 cent coin.
    eg. (assuming American coins: 1, 5, 10, and 25 cents)
    makeChange(1) = 1 (1)
    makeChange(6) = 2 (5 + 1)
    makeChange(49) = 7 (25 + 10 + 10 + 1 + 1 + 1 + 1)
     */
    public static void main(String[] args) {
        int[] coins = new int[]{10, 6, 1};
        System.out.println("Recursive: " + recursive(coins, 49));

        int[] cache = new int[50];
        for (int i = 0; i < cache.length; i++) {
            cache[i] = -1;
        }
        System.out.println("Recursive DP: " + recursiveDP(coins, cache, 49));

        for (int i = 0; i < cache.length; i++) {
            cache[i] = -1;
        }
        System.out.println("Iterative DP: " + iterativeDP(coins, cache, 49));
    }

    private static int recursive(int[] coins, int amt) {
        if (amt == 0) return 0;
        int minCoins = Integer.MAX_VALUE;
        for (int coin: coins) {
            if (amt - coin >= 0) {
                int currCoins = recursive(coins, amt - coin);
                if (currCoins < minCoins) {
                    minCoins = currCoins;
                }
            }
        }
        return minCoins + 1;
    }

    private static int recursiveDP(int[] coins, int[] cache, int amt) {
        if (amt == 0) {
            cache[amt] = 0;
            return 0;
        } else if (cache[amt] >= 0) {
            return cache[amt];
        }
        int minCoins = Integer.MAX_VALUE;
        for (int coin: coins) {
            if (amt - coin >= 0) {
                int currCoins = cache[amt - coin];
                if (currCoins < 0) {
                    currCoins = recursive(coins, amt - coin);
                }
                if (currCoins < minCoins) {
                    minCoins = currCoins;
                }
            }
        }
        return minCoins + 1;
    }

    private static int iterativeDP(int[] coins, int[] cache, int amt) {
        cache[0] = 0;
        for (int coinAmt = 1; coinAmt <= amt; coinAmt++) {
            int minCoins = Integer.MAX_VALUE;
            for (int coin : coins) {
                if (coinAmt - coin >= 0) {
                    int currCoins = cache[coinAmt - coin] + 1;
                    if (currCoins < minCoins) {
                        minCoins = currCoins;
                    }
                }
            }
            cache[coinAmt] = minCoins;
        }
        return cache[amt];
    }
}
