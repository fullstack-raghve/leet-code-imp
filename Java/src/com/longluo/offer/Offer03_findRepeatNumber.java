package com.longluo.offer;

import java.util.*;

/**
 * 剑指 Offer 03. 数组中重复的数字
 * <p>
 * 找出数组中重复的数字。
 * 在一个长度为 n 的数组nums里的所有数字都在 0～n-1 的范围内。数组中某些数字是重复的，但不知道有几个数字重复了，
 * 也不知道每个数字重复了几次。请找出数组中任意一个重复的数字。
 * <p>
 * 示例 1：
 * 输入：
 * [2, 3, 1, 0, 2, 5, 3]
 * 输出：2 或 3
 * <p>
 * 限制：
 * 2 <= n <= 100000
 * <p>
 * https://leetcode.cn/problems/shu-zu-zhong-zhong-fu-de-shu-zi-lcof/
 */
public class Offer03_findRepeatNumber {

    public static int findRepeatNumber(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            if (set.contains(num)) {
                return num;
            } else {
                set.add(num);
            }
        }

        return 0;
    }

    public static int findRepeatNumber2(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            if (map.containsKey(num)) {
                return num;
            } else {
                map.put(num, map.getOrDefault(num, 0) + 1);
            }
        }

        return 0;
    }

    public static int findRepeatNumber3(int[] nums) {
        if (nums == null || nums.length == 0) {
            return -1;
        }

        for (int i = 0; i < nums.length; i++) {
            if (i == nums[i]) {
                continue;
            }

            if (nums[i] == nums[nums[i]]) {
                return nums[i];
            }

            int temp = nums[i];
            nums[i] = nums[temp];
            nums[temp] = temp;
        }

        return -1;
    }

    public static void main(String[] args) {
        System.out.println("2 3 ?= " + findRepeatNumber(new int[]{2, 3, 1, 0, 2, 5, 3}));
        System.out.println("2 3 ?= " + findRepeatNumber2(new int[]{2, 3, 1, 0, 2, 5, 3}));
        System.out.println("2 3 ?= " + findRepeatNumber3(new int[]{2, 3, 1, 0, 2, 5, 3}));
    }
}
