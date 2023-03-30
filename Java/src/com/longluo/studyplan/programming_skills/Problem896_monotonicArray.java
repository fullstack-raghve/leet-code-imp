package com.longluo.studyplan.programming_skills;

/**
 * 896. 单调数列
 * <p>
 * 如果数组是单调递增或单调递减的，那么它是单调的。
 * 如果对于所有 i <= j，A[i] <= A[j]，那么数组 A 是单调递增的。 如果对于所有 i <= j，A[i]> = A[j]，那么数组 A 是单调递减的。
 * 当给定的数组 A 是单调数组时返回 true，否则返回 false。
 * <p>
 * 示例 1：
 * 输入：[1,2,2,3]
 * 输出：true
 * <p>
 * 示例 2：
 * 输入：[6,5,4,4]
 * 输出：true
 * <p>
 * 示例 3：
 * 输入：[1,3,2]
 * 输出：false
 * <p>
 * 示例 4：
 * 输入：[1,2,4,5]
 * 输出：true
 * <p>
 * 示例 5：
 * 输入：[1,1,1]
 * 输出：true
 * <p>
 * 提示：
 * 1 <= A.length <= 50000
 * -100000 <= A[i] <= 100000
 * <p>
 * https://leetcode-cn.com/problems/monotonic-array/
 */
public class Problem896_monotonicArray {

    // BF 两次判断 time: O(2 * n) space: O(1)
    public static boolean isMonotonic(int[] A) {
        if (A == null || A.length == 0) {
            return true;
        }

        int length = A.length;
        boolean isIncrease = false;
        if (A[length - 1] >= A[0]) {
            isIncrease = true;
        }

        for (int i = 1; i < length; i++) {
            if (isIncrease) {
                if (A[i] < A[i - 1]) {
                    return false;
                }
            } else {
                if (A[i] > A[i - 1]) {
                    return false;
                }
            }
        }

        return true;
    }

    // 子函数 time: O(2 * n) space: O(1)
    public static boolean isMonotonic_fun(int[] A) {
        return isSorted(A, true) || isSorted(A, false);
    }

    public static boolean isSorted(int[] A, boolean isInc) {
        for (int i = 1; i < A.length; i++) {
            if (isInc) {
                if (A[i] < A[i - 1]) {
                    return false;
                }
            } else {
                if (A[i] > A[i - 1]) {
                    return false;
                }
            }
        }

        return true;
    }

    // Best 一次遍历 time: O(n) space: O(1)
    public static boolean isMonotonic_best(int[] A) {
        boolean isInc = true;
        boolean isDec = true;
        for (int i = 1; i < A.length; i++) {
            if (A[i] < A[i - 1]) {
                isInc = false;
            }

            if (A[i] > A[i - 1]) {
                isDec = false;
            }
        }

        return isInc || isDec;
    }

    public static void main(String[] args) {
        System.out.println("true ?= " + isMonotonic(new int[]{1, 2, 2, 3}));
        System.out.println("true ?= " + isMonotonic_fun(new int[]{1, 2, 2, 3}));
        System.out.println("true ?= " + isMonotonic_best(new int[]{1, 2, 2, 3}));
        System.out.println("true ?= " + isMonotonic(new int[]{6, 5, 4, 4}));
        System.out.println("true ?= " + isMonotonic_fun(new int[]{6, 5, 4, 4}));
        System.out.println("true ?= " + isMonotonic_best(new int[]{6, 5, 4, 4}));
        System.out.println("false ?= " + isMonotonic(new int[]{1, 3, 2}));
        System.out.println("false ?= " + isMonotonic_fun(new int[]{1, 3, 2}));
        System.out.println("false ?= " + isMonotonic_best(new int[]{1, 3, 2}));
        System.out.println("true ?= " + isMonotonic(new int[]{1, 1, 1}));
        System.out.println("true ?= " + isMonotonic_fun(new int[]{1, 1, 1}));
        System.out.println("true ?= " + isMonotonic_best(new int[]{1, 1, 1}));
    }
}
