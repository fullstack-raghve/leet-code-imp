package com.longluo.studyplan.graph;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 695. 岛屿的最大面积
 * <p>
 * 给你一个大小为 m x n 的二进制矩阵 grid 。
 * 岛屿 是由一些相邻的 1 (代表土地) 构成的组合，这里的「相邻」要求两个 1 必须在 水平或者竖直的四个方向上 相邻。你可以假设 grid 的四个边缘都被 0（代表水）包围着。
 * 岛屿的面积是岛上值为 1 的单元格的数目。
 * 计算并返回 grid 中最大的岛屿面积。如果没有岛屿，则返回面积为 0 。
 * <p>
 * 示例 1：
 * 输入：grid = [[0,0,1,0,0,0,0,1,0,0,0,0,0],[0,0,0,0,0,0,0,1,1,1,0,0,0],[0,1,1,0,1,0,0,0,0,0,0,0,0],[0,1,0,0,1,1,0,0,1,0,1,0,0],[0,1,0,0,1,1,0,0,1,1,1,0,0],[0,0,0,0,0,0,0,0,0,0,1,0,0],[0,0,0,0,0,0,0,1,1,1,0,0,0],[0,0,0,0,0,0,0,1,1,0,0,0,0]]
 * 输出：6
 * 解释：答案不应该是 11 ，因为岛屿只能包含水平或垂直这四个方向上的 1 。
 * <p>
 * 示例 2：
 * 输入：grid = [[0,0,0,0,0,0,0,0]]
 * 输出：0
 * <p>
 * 提示：
 * m == grid.length
 * n == grid[i].length
 * 1 <= m, n <= 50
 * grid[i][j] 为 0 或 1
 * <p>
 * https://leetcode.com/problems/max-area-of-island/
 */
public class Problem695_maxAreaOfIsland {

    // BFS time: O(m*n) space: O(m*n)
    public static int maxAreaOfIsland(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return 0;
        }

        int row = grid.length;
        int col = grid[0].length;
        boolean[][] visited = new boolean[row][col];

        int max = 0;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (!visited[i][j] && grid[i][j] == 1) {
                    max = Math.max(max, bfs(grid, visited, i, j));
                }
            }
        }

        return max;
    }

    public static int bfs(int[][] grid, boolean[][] visited, int x, int y) {
        int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{x, y});
        visited[x][y] = true;
        int ans = 0;
        while (!queue.isEmpty()) {
            int[] pos = queue.poll();
            ans++;
            for (int[] dir : dirs) {
                int nextX = pos[0] + dir[0];
                int nextY = pos[1] + dir[1];
                if (nextX >= 0 && nextX < grid.length && nextY >= 0 && nextY < grid[0].length
                        && grid[nextX][nextY] == 1 && !visited[nextX][nextY]) {
                    queue.offer(new int[]{nextX, nextY});
                    visited[nextX][nextY] = true;
                }
            }
        }

        return ans;
    }

    public static void main(String[] args) {
        System.out.println("0 ?= " + maxAreaOfIsland(new int[][]{{0, 0, 0, 0, 0, 0, 0, 0}}));
    }
}