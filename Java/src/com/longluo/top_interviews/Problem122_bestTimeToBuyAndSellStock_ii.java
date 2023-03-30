package com.longluo.top_interviews;

/**
 * 122. 买卖股票的最佳时机 II
 * <p>
 * 给你一个整数数组 prices ，其中 prices[i] 表示某支股票第 i 天的价格。
 * <p>
 * 在每一天，你可以决定是否购买和/或出售股票。你在任何时候 最多 只能持有 一股 股票。你也可以先购买，然后在 同一天 出售。
 * <p>
 * 返回 你能获得的 最大 利润 。
 * <p>
 * 示例 1：
 * 输入：prices = [7,1,5,3,6,4]
 * 输出：7
 * 解释：在第 2 天（股票价格 = 1）的时候买入，在第 3 天（股票价格 = 5）的时候卖出, 这笔交易所能获得利润 = 5 - 1 = 4 。
 * 随后，在第 4 天（股票价格 = 3）的时候买入，在第 5 天（股票价格 = 6）的时候卖出, 这笔交易所能获得利润 = 6 - 3 = 3 。
 * 总利润为 4 + 3 = 7 。
 * <p>
 * 示例 2：
 * 输入：prices = [1,2,3,4,5]
 * 输出：4
 * 解释：在第 1 天（股票价格 = 1）的时候买入，在第 5 天 （股票价格 = 5）的时候卖出, 这笔交易所能获得利润 = 5 - 1 = 4 。
 * 总利润为 4 。
 * <p>
 * 示例 3：
 * 输入：prices = [7,6,4,3,1]
 * 输出：0
 * 解释：在这种情况下, 交易无法获得正利润，所以不参与交易可以获得最大利润，最大利润为 0 。
 * <p>
 * 提示：
 * 1 <= prices.length <= 3 * 10^4
 * 0 <= prices[i] <= 10^4
 * <p>
 * https://leetcode.com/problems/best-time-to-buy-and-sell-stock-ii/
 */
public class Problem122_bestTimeToBuyAndSellStock_ii {

    // DP time: O(n) space: O(n)
    public static int maxProfit(int[] prices) {
        int len = prices.length;
        int[][] dp = new int[len][2];

        dp[0][0] = 0;
        dp[0][1] = -prices[0];

        for (int i = 1; i < len; i++) {
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + prices[i]);
            dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] - prices[i]);
        }

        return dp[len - 1][0];
    }

    // DP Opt time: O(n) space: O(1)
    public static int maxProfit_opt(int[] prices) {
        int len = prices.length;

        int buy = -prices[0];
        int sell = 0;

        for (int i = 1; i < len; i++) {
            sell = Math.max(sell, buy + prices[i]);
            buy = Math.max(buy, sell - prices[i]);
        }

        return sell;
    }

    // Greedy time: O(n) space: O(1)
    public static int maxProfit_greedy(int[] prices) {
        int len = prices.length;

        int maxProfit = 0;
        int diff = 0;

        for (int i = 1; i < len; i++) {
            diff = prices[i] - prices[i - 1];
            if (diff > 0) {
                maxProfit += diff;
            }
        }

        return maxProfit;
    }

    public static void main(String[] args) {
        System.out.println("7 ?= " + maxProfit(new int[]{7, 1, 5, 3, 6, 4}));
        System.out.println("7 ?= " + maxProfit_opt(new int[]{7, 1, 5, 3, 6, 4}));
        System.out.println("7 ?= " + maxProfit_greedy(new int[]{7, 1, 5, 3, 6, 4}));
    }
}
