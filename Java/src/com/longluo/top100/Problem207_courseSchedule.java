package com.longluo.top100;

import java.util.*;

/**
 * 207. 课程表
 * <p>
 * 你这个学期必须选修 numCourses 门课程，记为 0 到 numCourses - 1 。
 * <p>
 * 在选修某些课程之前需要一些先修课程。 先修课程按数组 prerequisites 给出，其中 prerequisites[i] = [ai, bi] ，
 * 表示如果要学习课程 ai 则 必须 先学习课程  bi 。
 * <p>
 * 例如，先修课程对 [0, 1] 表示：想要学习课程 0 ，你需要先完成课程 1 。
 * 请你判断是否可能完成所有课程的学习？如果可以，返回 true ；否则，返回 false 。
 * <p>
 * 示例 1：
 * 输入：numCourses = 2, prerequisites = [[1,0]]
 * 输出：true
 * 解释：总共有 2 门课程。学习课程 1 之前，你需要完成课程 0 。这是可能的。
 * <p>
 * 示例 2：
 * 输入：numCourses = 2, prerequisites = [[1,0],[0,1]]
 * 输出：false
 * 解释：总共有 2 门课程。学习课程 1 之前，你需要先完成​课程 0 ；并且学习课程 0 之前，你还应先完成课程 1 。这是不可能的。
 * <p>
 * 提示：
 * 1 <= numCourses <= 10^5
 * 0 <= prerequisites.length <= 5000
 * prerequisites[i].length == 2
 * 0 <= ai, bi < numCourses
 * prerequisites[i] 中的所有课程对 互不相同
 * <p>
 * https://leetcode.cn/problems/course-schedule/
 */
public class Problem207_courseSchedule {

    // Topo Sorting time: O(v+e) space: O(v)
    public static boolean canFinish(int numCourses, int[][] prerequisites) {
        if (prerequisites == null || prerequisites.length == 0 || prerequisites[0].length == 0) {
            return true;
        }

        int[] indegree = new int[numCourses];
        for (int[] pre : prerequisites) {
            indegree[pre[0]]++;
        }

        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < numCourses; i++) {
            if (indegree[i] == 0) {
                queue.offer(i);
            }
        }

        while (!queue.isEmpty()) {
            Integer currId = queue.poll();
            numCourses--;
            for (int[] pre : prerequisites) {
                if (pre[1] == currId) {
                    indegree[pre[0]]--;
                    if (indegree[pre[0]] == 0) {
                        queue.offer(pre[0]);
                    }
                }
            }
        }

        return numCourses == 0;
    }

    // Topo Sort BFS
    public static boolean canFinish_topo(int numCourses, int[][] prerequisites) {
        Map<Integer, Integer> inDegreeMap = new HashMap<>();
        Map<Integer, Set<Integer>> graph = new HashMap<>();

        for (int[] edge : prerequisites) {
            int from = edge[1];
            int to = edge[0];

            graph.putIfAbsent(from, new HashSet<>());
            graph.get(from).add(to);
            inDegreeMap.put(to, inDegreeMap.getOrDefault(to, 0) + 1);
        }

        int count = 0;
        Queue<Integer> toVisit = new ArrayDeque<>();

        for (int i = 0; i < numCourses; i++) {
            if (!inDegreeMap.containsKey(i)) {
                toVisit.offer(i);
            }
        }

        while (!toVisit.isEmpty()) {
            int curNode = toVisit.poll();
            count++;

            if (!graph.containsKey(curNode)) {
                continue;
            }

            Set<Integer> neighbors = graph.get(curNode);
            for (int x : neighbors) {
                inDegreeMap.put(x, inDegreeMap.get(x) - 1);
                if (inDegreeMap.get(x) == 0) {
                    toVisit.offer(x);
                }
            }
        }

        return count == numCourses;
    }

    public static void main(String[] args) {
        System.out.println("true ?= " + canFinish(1, new int[][]{{}}));
        System.out.println("true ?= " + canFinish(2, new int[][]{{1, 0}}));
        System.out.println("false ?= " + canFinish(2, new int[][]{{1, 0}, {0, 1}}));

        System.out.println("false ?= " + canFinish_topo(2, new int[][]{{1, 0}, {0, 1}}));
    }
}
