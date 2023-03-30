package com.longluo.top_interviews;

import java.util.Arrays;

/**
 * 334. 递增的三元子序列
 * <p>
 * 给你一个整数数组 nums ，判断这个数组中是否存在长度为 3 的递增子序列。
 * 如果存在这样的三元组下标 (i, j, k) 且满足 i < j < k ，使得 nums[i] < nums[j] < nums[k] ，返回 true ；
 * 否则，返回 false 。
 * <p>
 * 示例 1：
 * 输入：nums = [1,2,3,4,5]
 * 输出：true
 * 解释：任何 i < j < k 的三元组都满足题意
 * <p>
 * 示例 2：
 * 输入：nums = [5,4,3,2,1]
 * 输出：false
 * 解释：不存在满足题意的三元组
 * <p>
 * 示例 3：
 * 输入：nums = [2,1,5,0,4,6]
 * 输出：true
 * 解释：三元组 (3, 4, 5) 满足题意，因为 nums[3] == 0 < nums[4] == 4 < nums[5] == 6
 * <p>
 * 提示：
 * 1 <= nums.length <= 5 * 10^5
 * -2^31 <= nums[i] <= 2^31 - 1
 * <p>
 * 进阶：你能实现时间复杂度为 O(n) ，空间复杂度为 O(1) 的解决方案吗？
 * <p>
 * https://leetcode.com/problems/increasing-triplet-subsequence/
 */
public class Problem334_increasingTripletSubsequence {

    // Simulate time: O(n^3) space: O(1)
    // TLE
    public static boolean increasingTriplet(int[] nums) {
        if (nums == null || nums.length < 3) {
            return false;
        }

        int len = nums.length;
        for (int i = 0; i < len; i++) {
            for (int j = i + 1; j < len; j++) {
                for (int k = j + 1; k < len; k++) {
                    if (nums[i] < nums[j] && nums[j] < nums[k]) {
                        return true;
                    }
                }
            }
        }

        return false;
    }

    // Simulate Opt time: O(n^3) space: O(1)
    // TLE
    public static boolean increasingTriplet_better(int[] nums) {
        if (nums == null || nums.length < 3) {
            return false;
        }

        int len = nums.length;
        for (int i = 0; i < len; i++) {
            for (int j = i + 1; j < len; j++) {
                if (nums[j] <= nums[i]) {
                    continue;
                }

                for (int k = j + 1; k < len; k++) {
                    if (nums[k] <= nums[j]) {
                        continue;
                    }

                    if (nums[i] < nums[j] && nums[j] < nums[k]) {
                        return true;
                    }
                }
            }
        }

        return false;
    }

    // DP time: O(n^2) space: O(n)
    public static boolean increasingTriplet_dp(int[] nums) {
        int len = nums.length;

        int[] dp = new int[len];
        Arrays.fill(dp, 1);

        for (int i = 0; i < len; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[j] < nums[i]) {
                    dp[i] = dp[j] + 1;
                }

                if (dp[i] >= 3) {
                    return true;
                }
            }
        }

        return false;
    }

    // Two Pointers time: O(n) space: O(n)
    public static boolean increasingTriplet_tp(int[] nums) {
        if (nums == null || nums.length < 3) {
            return false;
        }

        int len = nums.length;
        int[] leftMin = new int[len];
        leftMin[0] = nums[0];
        for (int i = 1; i < len; i++) {
            leftMin[i] = Math.min(leftMin[i - 1], nums[i]);
        }

        int[] rightMax = new int[len];
        rightMax[len - 1] = nums[len - 1];
        for (int i = len - 2; i >= 0; i--) {
            rightMax[i] = Math.max(rightMax[i + 1], nums[i]);
        }

        for (int i = 0; i < len; i++) {
            if (nums[i] > leftMin[i] && nums[i] < rightMax[i]) {
                return true;
            }
        }

        return false;
    }

    // Greedy time: O(n) space: O(1)
    public static boolean increasingTriplet_greedy(int[] nums) {
        if (nums == null || nums.length < 3) {
            return false;
        }

        int first = nums[0];
        int second = Integer.MAX_VALUE;

        for (int third : nums) {
            if (third > second) {
                return true;
            } else if (third > first) {
                second = third;
            } else {
                first = third;
            }
        }

        return false;
    }

    public static void main(String[] args) {
        System.out.println("true ?= " + increasingTriplet(new int[]{1, 2, 3, 4, 5}));
        System.out.println("false ?= " + increasingTriplet(new int[]{5, 4, 3, 2, 1}));
        System.out.println("true ?= " + increasingTriplet(new int[]{2, 1, 5, 0, 4, 6}));
        System.out.println("true ?= " + increasingTriplet_better(new int[]{2, 1, 5, 0, 4, 6}));

        System.out.println("true ?= " + increasingTriplet_dp(new int[]{2, 1, 5, 0, 4, 6}));

        System.out.println("true ?= " + increasingTriplet_greedy(new int[]{1, 2, 3, 4, 5}));
        System.out.println("true ?= " + increasingTriplet_greedy(new int[]{2, 1, 5, 0, 4, 6}));
        System.out.println("true ?= " + increasingTriplet_greedy(new int[]{20, 100, 10, 12, 5, 13}));
        System.out.println("false ?= " + increasingTriplet_greedy(new int[]{5, 1, 6}));

        System.out.println("false ?= " + increasingTriplet_tp(new int[]{5, 4, 3, 2, 1}));
        System.out.println("true ?= " + increasingTriplet_tp(new int[]{20, 100, 10, 12, 5, 13}));
    }
}
