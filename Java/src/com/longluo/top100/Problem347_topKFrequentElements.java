package com.longluo.top100;

import java.util.*;

/**
 * 347. 前 K 个高频元素
 * <p>
 * 给你一个整数数组 nums 和一个整数 k ，请你返回其中出现频率前 k 高的元素。你可以按 任意顺序 返回答案。
 * <p>
 * 示例 1:
 * 输入: nums = [1,1,1,2,2,3], k = 2
 * 输出: [1,2]
 * <p>
 * 示例 2:
 * 输入: nums = [1], k = 1
 * 输出: [1]
 * <p>
 * 提示：
 * 1 <= nums.length <= 10^5
 * k 的取值范围是 [1, 数组中不相同的元素的个数]
 * 题目数据保证答案唯一，换句话说，数组中前 k 个高频元素的集合是唯一的
 * <p>
 * 进阶：你所设计算法的时间复杂度 必须 优于 O(n log n) ，其中 n 是数组大小。
 * <p>
 * https://leetcode.com/problems/top-k-frequent-elements/
 */
public class Problem347_topKFrequentElements {

    // HashMap + Sort time: O(nlogn) space: O(n)
    public static int[] topKFrequent_hash(int[] nums, int k) {
        Map<Integer, Integer> freq = new HashMap<>();
        for (int x : nums) {
            freq.put(x, freq.getOrDefault(x, 0) + 1);
        }

        List<int[]> freqList = new ArrayList<>();
        for (Map.Entry<Integer, Integer> entry : freq.entrySet()) {
            freqList.add(new int[]{entry.getKey(), entry.getValue()});
        }

        Collections.sort(freqList, (o1, o2) -> {
            if (o1[1] == o2[1]) {
                return o1[0] - o2[0];
            }
            return o2[1] - o1[1];
        });

        int[] ans = new int[k];
        for (int i = 0; i < k; i++) {
            ans[i] = freqList.get(i)[0];
        }

        return ans;
    }

    // HashMap + Heap time: O(nlogn) space: O(n)
    public static int[] topKFrequent_pq(int[] nums, int k) {
        Map<Integer, Integer> freqMap = new HashMap<>();
        for (int x : nums) {
            freqMap.put(x, freqMap.getOrDefault(x, 0) + 1);
        }

        PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o2[1] - o1[1];
            }
        });

        for (int x : freqMap.keySet()) {
            pq.offer(new int[]{x, freqMap.get(x)});
        }

        int[] ans = new int[k];
        while (k > 0 && !pq.isEmpty()) {
            int[] curr = pq.poll();
            ans[k - 1] = curr[0];
            k--;
        }

        return ans;
    }

    public static void main(String[] args) {
        System.out.println("[1, 2] ?= " + Arrays.toString(topKFrequent_hash(new int[]{1, 1, 1, 2, 2, 3}, 2)));
        System.out.println("[1, 2] ?= " + Arrays.toString(topKFrequent_pq(new int[]{1, 1, 1, 2, 2, 3}, 2)));
    }
}
