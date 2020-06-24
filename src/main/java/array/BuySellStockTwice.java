package array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BuySellStockTwice {
    public static void main(String[] args) {
        System.out.println(compute(Arrays.asList(12.0,11.0,13.0,9.0,12.0,8.0,14.0,13.0,15.0)));
    }

    public static Double compute(List<Double> prices) {
        double maxTotalProfit = 0.0;
        List<Double> firstBuySellProfits = new ArrayList<>();

        double minPriceSoFar = Double.MAX_VALUE;
        //compute first buy-sell profit by each day
        for (int i = 0; i < prices.size();++i) {
            minPriceSoFar = Math.min(minPriceSoFar, prices.get(i));
            maxTotalProfit = Math.max(maxTotalProfit, prices.get(i) - minPriceSoFar);
            firstBuySellProfits.add(maxTotalProfit);
        }
        // Backward phase. For each day, find the maximum profit if we make
        // the second buy on that day.
        double maxPriceSoFar = Double.MIN_VALUE;
        for (int i = prices.size() - 1; i > 0; --i) {
            maxPriceSoFar = Math.max(maxPriceSoFar , prices.get(i));
            maxTotalProfit = Math.max(maxTotalProfit , maxPriceSoFar - prices.get(i)
                                      + firstBuySellProfits.get(i - 1));
        }
        return maxTotalProfit;
    }
}
