package com.longluo.top100;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 39. 组合总和
 * <p>
 * 给你一个 无重复元素 的整数数组 candidates 和一个目标整数 target ，找出 candidates 中可以使数字和为目标数 target 的 所有 不同组合 ，
 * 并以列表形式返回。你可以按 任意顺序 返回这些组合。
 * candidates 中的 同一个 数字可以 无限制重复被选取 。如果至少一个数字的被选数量不同，则两种组合是不同的。
 * <p>
 * 对于给定的输入，保证和为 target 的不同组合数少于 150 个。
 * <p>
 * 示例 1：
 * 输入：candidates = [2,3,6,7], target = 7
 * 输出：[[2,2,3],[7]]
 * 解释：
 * 2 和 3 可以形成一组候选，2 + 2 + 3 = 7 。注意 2 可以使用多次。
 * 7 也是一个候选， 7 = 7 。
 * 仅有这两种组合。
 * <p>
 * 示例 2：
 * 输入: candidates = [2,3,5], target = 8
 * 输出: [[2,2,2,2],[2,3,3],[3,5]]
 * <p>
 * 示例 3：
 * 输入: candidates = [2], target = 1
 * 输出: []
 * <p>
 * 提示：
 * 1 <= candidates.length <= 30
 * 1 <= candidates[i] <= 200
 * candidate 中的每个元素都 互不相同
 * 1 <= target <= 500
 * <p>
 * https://leetcode.com/problems/combination-sum/
 */
public class Problem39_combinationSum {

    // Backtrack
    // time: O(S), 其中 S 为所有可行解的长度之和
    // space: O(target)
    public static List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> ans = new ArrayList<>();
        if (candidates == null || target == 0) {
            return ans;
        }

        // for trim
        Arrays.sort(candidates);
        backtrack(ans, new ArrayList<>(), candidates, 0, target);
        return ans;
    }

    public static void backtrack(List<List<Integer>> res, List<Integer> list, int[] candidates, int begin, int remain) {
        if (remain < 0) {
            return;
        }

        if (remain == 0) {
            res.add(new ArrayList<>(list));
            return;
        }

        int len = candidates.length;
        for (int i = begin; i < len; i++) {
            int num = candidates[i];
            list.add(num);
            backtrack(res, list, candidates, i, remain - num);
            list.remove(list.size() - 1);
        }
    }

    public static void backtrack_trim(List<List<Integer>> res, List<Integer> list, int[] candidates, int begin, int remain) {
        if (remain < 0) {
            return;
        }

        if (remain == 0) {
            res.add(new ArrayList<>(list));
            return;
        }

        int len = candidates.length;
        for (int i = begin; i < len; i++) {
            int num = candidates[i];
            if (remain - num < 0) {
                break;
            }
            list.add(num);
            backtrack_trim(res, list, candidates, i, remain - num);
            list.remove(list.size() - 1);
        }
    }

    // TODO: 2022/5/9


    public static void main(String[] args) {

    }
}
