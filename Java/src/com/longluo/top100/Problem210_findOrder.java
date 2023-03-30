package com.longluo.top100;

import java.util.*;

/**
 * 210. 课程表 II
 * <p>
 * 现在你总共有 numCourses 门课需要选，记为 0 到 numCourses - 1。给你一个数组 prerequisites ，
 * 其中 prerequisites[i] = [ai, bi] ，表示在选修课程 ai 前 必须 先选修 bi 。
 * <p>
 * 例如，想要学习课程 0 ，你需要先完成课程 1 ，我们用一个匹配来表示：[0,1] 。
 * 返回你为了学完所有课程所安排的学习顺序。可能会有多个正确的顺序，你只要返回 任意一种 就可以了。
 * 如果不可能完成所有课程，返回 一个空数组 。
 * <p>
 * 示例 1：
 * 输入：numCourses = 2, prerequisites = [[1,0]]
 * 输出：[0,1]
 * 解释：总共有 2 门课程。要学习课程 1，你需要先完成课程 0。因此，正确的课程顺序为 [0,1] 。
 * <p>
 * 示例 2：
 * 输入：numCourses = 4, prerequisites = [[1,0],[2,0],[3,1],[3,2]]
 * 输出：[0,2,1,3]
 * 解释：总共有 4 门课程。要学习课程 3，你应该先完成课程 1 和课程 2。并且课程 1 和课程 2 都应该排在课程 0 之后。
 * 因此，一个正确的课程顺序是 [0,1,2,3] 。另一个正确的排序是 [0,2,1,3] 。
 * <p>
 * 示例 3：
 * 输入：numCourses = 1, prerequisites = []
 * 输出：[0]
 * <p>
 * 提示：
 * 1 <= numCourses <= 2000
 * 0 <= prerequisites.length <= numCourses * (numCourses - 1)
 * prerequisites[i].length == 2
 * 0 <= ai, bi < numCourses
 * ai != bi
 * 所有[ai, bi] 互不相同
 * <p>
 * https://leetcode.cn/problems/course-schedule-ii/
 */
public class Problem210_findOrder {

    // Topo Sort
    public static int[] findOrder(int numCourses, int[][] prerequisites) {
        if (numCourses < 0 || prerequisites == null || prerequisites.length == 0) {
            return new int[0];
        }

        int[] result = new int[numCourses];

        int[] inDegres = new int[numCourses];
        HashSet<Integer>[] adj = new HashSet[numCourses];
        Queue<Integer> queue = new LinkedList<>();

        for (int i = 0; i < numCourses; i++) {
            adj[i] = new HashSet<>();
        }

        for (int[] pre : prerequisites) {
            adj[pre[1]].add(pre[0]);
            inDegres[pre[0]]++;
        }

        for (int i = 0; i < numCourses; i++) {
            if (inDegres[i] == 0) {
                queue.offer(i);
            }
        }

        int count = 0;
        while (!queue.isEmpty()) {
            Integer node = queue.poll();
            inDegres[node]--;
            result[count] = node;
            count++;
            Set<Integer> set = adj[node];
            for (Integer child : set) {
                inDegres[child]--;
                if (inDegres[child] == 0) {
                    queue.offer(child);
                }
            }
        }

        if (count == numCourses) {
            return result;
        }

        return new int[0];
    }

    // Topo Sort time: O(v+e) space: O(n)
    public static int[] findOrder_topo(int numCourses, int[][] prerequisites) {
        Map<Integer, Integer> inDegrees = new HashMap<>();
        Map<Integer, Set<Integer>> graph = new HashMap<>();

        // Build Graph
        for (int[] edge : prerequisites) {
            int from = edge[1];
            int to = edge[0];

            graph.putIfAbsent(from, new HashSet<>());
            graph.get(from).add(to);

            inDegrees.put(to, inDegrees.getOrDefault(to, 0) + 1);
        }

        return bfs(graph, inDegrees, numCourses);
    }

    private static int[] bfs(Map<Integer, Set<Integer>> graph, Map<Integer, Integer> inDegreesMap, int nodeNum) {
        int[] res = new int[nodeNum];

        // count used for attended courses
        int count = 0;

        // visit non-pre courses first
        Queue<Integer> toVisit = new ArrayDeque<>();
        for (int i = 0; i < nodeNum; i++) {
            if (!inDegreesMap.containsKey(i)) {
                toVisit.offer(i);
            }
        }

        while (!toVisit.isEmpty()) {
            int curNode = toVisit.poll();

            res[count] = curNode;
            count++;

            if (!graph.containsKey(curNode)) {
                continue;
            }

            Set<Integer> neighbors = graph.get(curNode);
            for (int x : neighbors) {
                inDegreesMap.put(x, inDegreesMap.get(x) - 1);
                if (inDegreesMap.get(x) == 0) {
                    toVisit.offer(x);
                }
            }
        }

        return count == nodeNum ? res : new int[0];
    }

    // TODO: 2022/9/14
    // https://leetcode.cn/problems/course-schedule-ii/solution/java-jian-dan-hao-li-jie-de-tuo-bu-pai-xu-by-kelly/
    private static boolean dfs(Map<Integer, Set<Integer>> graph, Map<Integer, Integer> inDegreesMap, int nodeNum) {

        return false;
    }

    public static void main(String[] args) {
        System.out.println("[0, 1] ?= " + Arrays.toString(findOrder(2, new int[][]{{1, 0}})));

        System.out.println("[0, 1] ?= " + Arrays.toString(findOrder_topo(2, new int[][]{{1, 0}})));
    }
}
