package com.student.compete.leetcode.dp.easy;

import java.util.Arrays;

public class BestTimeToBuyAndSellStock {

    // study more
    // https://leetcode.com/problems/best-time-to-buy-and-sell-stock/description/

    // solution 1
    public int maxProfit(int[] prices) {
        if (prices.length <= 1) {
            return 0;
        }
        if (prices.length == 2) {
            return Integer.max(0, prices[1] - prices[0]);
        }
        int[] profitDP = getProfitDP(prices);
        return Arrays.stream(profitDP).max().orElse(0);
    }

    private int[] getProfitDP(int[] prices) {

        int[] profitDP = new int[prices.length];

        profitDP[0] = 0;
        profitDP[1] = prices[1] - prices[0];
        profitDP[2] = Integer.max(prices[2] - prices[1], prices[2] - prices[0]);

        for (int day = 3; day <= prices.length - 1; day++) {

            // if i sell today and buy yesterday
            int profit1 = prices[day] - prices[day - 1];

            // if i sell today and buy some best day before yesterday
            int profit2 = prices[day] + profitDP[day - 1] - prices[day - 1];

            profitDP[day] = Integer.max(profit1, profit2);
        }

        return profitDP;
    }

//    // solution 2
//    public int maxProfit(int[] prices) {
//        if (prices.length <= 1) {
//            return 0;
//        }
//        if (prices.length == 2) {
//            return Integer.max(0, prices[1] - prices[0]);
//        }
//        int[] bestBuyingDayDP = getBestBuyingDayDP(prices);
//
//        int profit = 0;
//        for (int day = 1; day <= prices.length - 1; day++) {
//            if(profit < prices[day] - prices[bestBuyingDayDP[day - 1]]) {
//                profit = prices[day] - prices[bestBuyingDayDP[day - 1]];
//            }
//        }
//
//        return profit;
//    }
//
//    private int[] getBestBuyingDayDP(int[] prices) {
//
//        int[] bestBuyingDayDP = new int[prices.length];
//
//        bestBuyingDayDP[0] = 0;
//
//        for (int day = 1; day <= prices.length - 1; day++) {
//            int bestBuyingDay = bestBuyingDayDP[day - 1];
//            if(prices[day] < prices[bestBuyingDay]) {
//                bestBuyingDay = day;
//            }
//            bestBuyingDayDP[day] = bestBuyingDay;
//        }
//
//        return bestBuyingDayDP;
//    }

}
