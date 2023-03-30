package com.longluo.studyplan.meituan.day1.storemanager;

import java.util.Arrays;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.TreeSet;

/**
 * meituan-002. 小美的仓库整理
 * <p>
 * 小美是美团仓库的管理员，她会根据单据的要求按顺序取出仓库中的货物，每取出一件货物后会把剩余货物重新堆放，使得自己方便查找。
 * 已知货物入库的时候是按顺序堆放在一起的。如果小美取出其中一件货物，则会把货物所在的一堆物品以取出的货物为界分成两堆，
 * 这样可以保证货物局部的顺序不变。
 * 已知货物最初是按 1~n 的顺序堆放的，每件货物的重量为 w[i] ,小美会根据单据依次不放回的取出货物。请问根据上述操作，
 * 小美每取出一件货物之后，重量和最大的一堆货物重量是多少？
 * <p>
 * 格式：
 * 输入：
 * - 输入第一行包含一个正整数 n ，表示货物的数量。
 * - 输入第二行包含 n 个正整数，表示 1~n 号货物的重量 w[i] 。
 * - 输入第三行有 n 个数，表示小美按顺序取出的货物的编号，也就是一个 1~n 的全排列。
 * 输出：
 * - 输出包含 n 行，每行一个整数，表示每取出一件货物以后，对于重量和最大的一堆货物，其重量和为多少。
 * <p>
 * 示例：
 * 输入：
 * 5
 * 3 2 4 4 5
 * 4 3 5 2 1
 * 输出：
 * 9
 * 5
 * 5
 * 3
 * 0
 * <p>
 * 解释：
 * 原本的状态是 {{3,2,4,4,5}} ，取出 4 号货物后，得到 {{3,2,4},{5}} ，第一堆货物的和是 9 ，然后取出 3 号货物得到 {{3,2}{5}} ，
 * 此时第一堆和第二堆的和都是 5 ，以此类推。
 * <p>
 * 提示：
 * 1 <= n,m <= 50000
 * 1 <= w[i] <= 100
 * <p>
 * https://leetcode-cn.com/problems/TJZLyC/
 */
public class Solution {

    /*
5
3 2 4 4 5
4 3 5 2 1
    */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = Integer.parseInt(scanner.nextLine());

        String[] weights = scanner.nextLine().split(" ");
        int[] weight = new int[n + 1];
        int[] prefixSum = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            weight[i] = Integer.parseInt(weights[i - 1]);
            prefixSum[i] = prefixSum[i - 1] + weight[i];
        }

        String[] number = scanner.nextLine().split(" ");

        int[][] bounds = new int[n + 1][2];
        for (int i = 0; i <= n; i++) {
            bounds[i] = new int[]{-1, -1};
        }

        int[] ans = new int[n];

        int maxWeight = 0;
        for (int i = n - 1; i >= 0; i--) {
            int x = Integer.parseInt(number[i]);
            ans[i] = maxWeight;
            if (i == 0) {
                break;
            }
            // 更新最大重量
            int cur = weight[x];
            int left = x;
            int right = x;//左边界和右边界,注意如果左右无连通区域则区间为[x,x],所以初始化为x
            //每次只会将左右两块区域连成一块,我们只需关心一段区间的左边界和右边界,就能通过前缀和数组查询到区间和
            if (x + 1 <= n && bounds[x + 1][0] != -1) {
                cur += prefixSum[bounds[x + 1][1]] - prefixSum[bounds[x + 1][0] - 1];
                right = bounds[x + 1][1]; //更新右边界
            }
            if (x - 1 > 0 && bounds[x - 1][1] != -1) {
                cur += prefixSum[bounds[x - 1][1]] - prefixSum[bounds[x - 1][0] - 1];
                left = bounds[x - 1][0]; //更新左边界
            }

            maxWeight = Math.max(maxWeight, cur);
            // 更新两端点的左右区间
            bounds[left][0] = left;
            bounds[left][1] = right;
            bounds[right][0] = left;
            bounds[right][1] = right;
        }

        for (int i = 0; i < n; i++) {
            System.out.println(ans[i]);
        }
    }

    /*
    // Wrong Method for not only 2 packages.
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        scanner.nextLine();
        int[] weight = new int[n];
        for (int i = 0; i < n; i++) {
            weight[i] = scanner.nextInt();
        }

        int[] number = new int[n];
        for (int i = 0; i < n; i++) {
            number[i] = scanner.nextInt();
        }

        int[] ans = new int[n];
        for (int i = 0; i < n; i++) {
            weight[number[i] - 1] = 0;
            ans[i] = getMax(weight, number[i]);
        }

        for (int i = 0; i < n; i++) {
            System.out.println(ans[i]);
        }
    }

    private static int getMax(int[] weight, int no) {
        int leftTotal = 0;
        int rightTotal = 0;
        for (int i = 0; i < (no - 1); i++) {
            leftTotal += weight[i];
        }

        for (int i = no; i < weight.length; i++) {
            rightTotal += weight[i];
        }

        return Math.max(leftTotal, rightTotal);
    }
    */

    /*
    // Okay
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        scanner.nextLine();
        int[] weight = new int[n];
        for (int i = 0; i < n; i++) {
            weight[i] = scanner.nextInt();
        }

        int[] number = new int[n];
        for (int i = 0; i < n; i++) {
            number[i] = scanner.nextInt();
        }

        int[] ans = new int[n];
        for (int i = 0; i < n; i++) {
            weight[number[i] - 1] = 0;
            ans[i] = getMax(weight);
        }

        for (int i = 0; i < n; i++) {
            System.out.println(ans[i]);
        }
    }

    private static int getMax(int[] weight) {
        int maxSum = 0;
        int n = weight.length;
        int idx = 0;
        while (idx < n) {
            while (idx < n && weight[idx] == 0) {
                idx++;
            }

            if (idx < n && weight[idx] != 0) {
                int sum = 0;
                while (idx < n && weight[idx] != 0) {
                    sum += weight[idx];
                    idx++;
                }

                maxSum = Math.max(sum, maxSum);
            }
        }

        return maxSum;
    }
     */

    /*
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        scanner.nextLine();
        int[] weight = new int[n];
        for (int i = 0; i < n; i++) {
            weight[i] = scanner.nextInt();
        }

        int[] number = new int[n];
        for (int i = 0; i < n; i++) {
            number[i] = scanner.nextInt();
        }

        int[] prefixSum = new int[n + 1];
        for (int i = 0; i < n; i++) {
            prefixSum[i + 1] = weight[i] + prefixSum[i];
        }

        int[] ans = new int[n];

        TreeSet<Integer> boundSet = new TreeSet<>();
        boundSet.add(0);
        boundSet.add(n + 1);

        TreeMap<Integer, Integer> sumMap = new TreeMap<>();

        for (int i = 0; i < n; i++) {
            int pos = number[i];
            int left = boundSet.lower(pos);
            int right = boundSet.higher(pos);
            boundSet.add(pos);

            int segSum = prefixSum[right - 1] - prefixSum[left];
            Integer count = sumMap.get(segSum);
            if (count != null) {
                if (count == 1) {
                    sumMap.remove(segSum);
                } else {
                    sumMap.put(segSum, count - 1);
                }
            }

            int leftSum = prefixSum[pos - 1] - prefixSum[left];
            int rightSum = prefixSum[right - 1] - prefixSum[pos];
            sumMap.put(leftSum, sumMap.getOrDefault(leftSum, 0) + 1);
            sumMap.put(rightSum, sumMap.getOrDefault(rightSum, 0) + 1);
            ans[i] = sumMap.lastKey();
        }

        for (int i = 0; i < n; i++) {
            System.out.println(ans[i]);
        }
    }
    */
}
