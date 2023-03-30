package com.longluo.top100;

/**
 * 31. 下一个排列
 * <p>
 * 实现获取 下一个排列 的函数，算法需要将给定数字序列重新排列成字典序中下一个更大的排列（即，组合出下一个更大的整数）。
 * 如果不存在下一个更大的排列，则将数字重新排列成最小的排列（即升序排列）。
 * 必须 原地 修改，只允许使用额外常数空间。
 * <p>
 * 示例 1：
 * 输入：nums = [1,2,3]
 * 输出：[1,3,2]
 * <p>
 * 示例 2：
 * 输入：nums = [3,2,1]
 * 输出：[1,2,3]
 * <p>
 * 示例 3：
 * 输入：nums = [1,1,5]
 * 输出：[1,5,1]
 * <p>
 * 示例 4：
 * 输入：nums = [1]
 * 输出：[1]
 * <p>
 * 提示：
 * 1 <= nums.length <= 100
 * 0 <= nums[i] <= 100
 * <p>
 * https://leetcode-cn.com/problems/next-permutation/
 * <p>
 * https://leetcode.com/problems/next-permutation/
 */
public class Problem31_nextPermutation {

    // Two Pointers time: O(n) space: O(1)
    public static void nextPermutation_tp(int[] nums) {
        if (nums == null || nums.length <= 1) {
            return;
        }

        int len = nums.length;
        int left = len - 2;
        /**
         * from right to left, search for the first one which is smaller than the right digit.
         */
        while (left >= 0 && nums[left] >= nums[left + 1]) {
            left--;
        }

        /**
         *  If the one exists, search a one which is larger than it from right to left.
         */
        if (left >= 0) {
            int right = nums.length - 1;
            while (right >= 0 && nums[left] >= nums[right]) {
                right--;
            }
            /**
             * swap them.
             */
            swap(nums, left, right);
        }

        /**
         *  flip the right to make the number smaller.
         */
        reverse(nums, left + 1);
    }

    public static void swap(int[] nums, int a, int b) {
        int temp = nums[a];
        nums[a] = nums[b];
        nums[b] = temp;
    }

    public static void reverse(int[] nums, int low) {
        int left = low;
        int right = nums.length - 1;
        while (left < right) {
            swap(nums, left, right);
            left++;
            right--;
        }
    }

    public static void main(String[] args) {
        nextPermutation_tp(new int[]{1});
        nextPermutation_tp(new int[]{1, 2, 3});
        nextPermutation_tp(new int[]{3, 2, 1});
        nextPermutation_tp(new int[]{1, 3, 2});
    }
}
