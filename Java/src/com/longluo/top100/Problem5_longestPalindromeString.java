package com.longluo.top100;

/**
 * 5. Longest Palindromic Substring
 * <p>
 * Medium
 * <p>
 * Given a string s, return the longest palindromic substring in s.
 * <p>
 * Example 1:
 * Input: s = "babad"
 * Output: "bab"
 * Explanation: "aba" is also a valid answer.
 * <p>
 * Example 2:
 * Input: s = "cbbd"
 * Output: "bb"
 * <p>
 * Constraints:
 * 1 <= s.length <= 1000
 * s consist of only digits and English letters.
 * <p>
 * https://leetcode.com/problems/longest-palindromic-substring/
 */
public class Problem5_longestPalindromeString {

    // BF time: O(n^3) space: O(n)
    public static String longestPalindrome_bf(String s) {
        if (s == null || s.length() <= 1) {
            return s;
        }

        int len = s.length();
        int maxLen = 1;
        int left = 0;
        char[] array = s.toCharArray();
        for (int i = 0; i < len; i++) {
            for (int j = i + 1; j < len; j++) {
                if (j - i + 1 >= maxLen && validPalindrome(array, i, j)) {
                    maxLen = Math.max(maxLen, j - i + 1);
                    left = i;
                }
            }
        }

        return s.substring(left, left + maxLen);
    }

    public static boolean validPalindrome(char[] array, int left, int right) {
        while (left < right) {
            if (array[left] != array[right]) {
                return false;
            }

            left++;
            right--;
        }

        return true;
    }

    // DP time: O(n^2) space: O(n^2)
    public static String longestPalindrome_dp(String s) {
        int len = s.length();
        if (len < 2) {
            return s;
        }

        int maxLen = 1;
        int left = 0;

        boolean[][] dp = new boolean[len][len];

        for (int i = 0; i < len; i++) {
            dp[i][i] = true;
        }

        for (int j = 1; j < len; j++) {
            for (int i = 0; i < j; i++) {
                if (s.charAt(i) == s.charAt(j)) {
                    if (j - i < 3) {
                        dp[i][j] = true;
                    } else {
                        dp[i][j] = dp[i + 1][j - 1];
                    }
                }

                if (dp[i][j] && j - i + 1 > maxLen) {
                    maxLen = j - i + 1;
                    left = i;
                }
            }
        }

        return s.substring(left, left + maxLen);
    }

    public static void main(String[] args) {
        System.out.println("bab ?= " + longestPalindrome_bf("babad"));
        System.out.println("bb ?= " + longestPalindrome_bf("cbbd"));

        System.out.println("bab ?= " + longestPalindrome_dp("babad"));
        System.out.println("bb ?= " + longestPalindrome_dp("cbbd"));
    }
}
