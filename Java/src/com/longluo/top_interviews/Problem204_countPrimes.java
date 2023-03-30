package com.longluo.top_interviews;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 204. 计数质数
 * <p>
 * 统计所有小于非负整数n的质数的数量。
 * <p>
 * 示例 1：
 * 输入：n = 10
 * 输出：4
 * 解释：小于 10 的质数一共有 4 个, 它们是 2, 3, 5, 7。
 * <p>
 * 示例 2：
 * 输入：n = 0
 * 输出：0
 * <p>
 * 示例 3：
 * 输入：n = 1
 * 输出：0
 * <p>
 * 提示：
 * 0 <= n <= 5 * 10^6
 * <p>
 * https://leetcode.com/problems/count-primes/
 */
public class Problem204_countPrimes {

    // BF time: O(n*sqrt(n)) space: O(1)
    // TLE
    public static int countPrimes_bf(int n) {
        if (n <= 1) {
            return 0;
        }

        int ans = 0;
        for (int i = 2; i < n; i++) {
            if (isPrime(i)) {
                ans++;
            }
        }

        return ans;
    }

    private static boolean isPrime(int n) {
        for (int i = 2; i * i <= n; i++) {
            if (n % i == 0) {
                return false;
            }
        }

        return n >= 2;
    }

    // 质数筛法 time: O(nloglogn) space: O(n)
    public static int countPrimes(int n) {
        if (n <= 1) {
            return 0;
        }

        int count = 0;
        int[] isPrime = new int[n];
        Arrays.fill(isPrime, 1);

        for (int i = 2; i < n; i++) {
            if (isPrime[i] == 0) {
                continue;
            }

            count++;

            if ((long) i * i < n) {
                for (int j = i * i; j < n; j += i) {
                    isPrime[j] = 0;
                }
            }
        }

        return count;
    }

    public static int countPrimes_sieve(int n) {
        if (n <= 1) {
            return 0;
        }

        int count = 0;
        boolean[] cells = new boolean[n];
        Arrays.fill(cells, true);
        for (int i = 2; i < n; i++) {
            if (!cells[i]) {
                continue;
            }

            for (int j = 2; j * i < n; j++) {
                cells[j * i] = false;
            }
        }

        for (int i = 2; i < n; i++) {
            if (cells[i]) {
                count++;
            }
        }

        return count;
    }

    public static int countPrimes_sieve_better(int n) {
        if (n <= 1) {
            return 0;
        }

        int count = 0;
        int[] cells = new int[n];
        Arrays.fill(cells, 1);
        for (int i = 2; i < n; i++) {
            if (cells[i] == 1) {
                count++;

                if ((long) i * i < n) {
                    for (int j = i * i; j < n; j += i) {
                        cells[j] = 0;
                    }
                }
            }
        }

        return count;
    }

    public static int countPrimes_linear(int n) {
        List<Integer> primes = new ArrayList<Integer>();
        int[] isPrime = new int[n];
        Arrays.fill(isPrime, 1);
        for (int i = 2; i < n; ++i) {
            if (isPrime[i] == 1) {
                primes.add(i);
            }
            for (int j = 0; j < primes.size() && i * primes.get(j) < n; ++j) {
                isPrime[i * primes.get(j)] = 0;
                if (i % primes.get(j) == 0) {
                    break;
                }
            }
        }

        return primes.size();
    }

    public static void main(String[] args) {
        System.out.println("false ?= " + isPrime(0));
        System.out.println("false ?= " + isPrime(1));
        System.out.println("true ?= " + isPrime(2));
        System.out.println("true ?= " + isPrime(3));
        System.out.println("false ?= " + isPrime(10));

        System.out.println("0 ?= " + countPrimes_bf(2));

        System.out.println("4 ?= " + countPrimes(10));
        System.out.println("0 ?= " + countPrimes(0));
        System.out.println("0 ?= " + countPrimes(1));

        System.out.println("4 ?= " + countPrimes_bf(10));
        System.out.println("4 ?= " + countPrimes_sieve(10));

        System.out.println("4 ?= " + countPrimes_sieve_better(10));
        System.out.println("4 ?= " + countPrimes_linear(10));


//        for (int i = 2; i < 5000000; i++) {
//            if (isPrimeNumber(i)) {
//                System.out.print(i + ", ");
//            }
//        }
    }
}
