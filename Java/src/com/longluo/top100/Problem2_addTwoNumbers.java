package com.longluo.top100;

import com.longluo.datastructure.LinkedListNodeUtils;
import com.longluo.datastructure.ListNode;

/**
 * 2. 两数相加
 * <p>
 * 给你两个 非空 的链表，表示两个非负的整数。它们每位数字都是按照 逆序 的方式存储的，并且每个节点只能存储 一位 数字。
 * 请你将两个数相加，并以相同形式返回一个表示和的链表。
 * 你可以假设除了数字 0 之外，这两个数都不会以 0 开头。
 * <p>
 * 示例 1：
 * 输入：l1 = [2,4,3], l2 = [5,6,4]
 * 输出：[7,0,8]
 * 解释：342 + 465 = 807.
 * <p>
 * 示例 2：
 * 输入：l1 = [0], l2 = [0]
 * 输出：[0]
 * <p>
 * 示例 3：
 * 输入：l1 = [9,9,9,9,9,9,9], l2 = [9,9,9,9]
 * 输出：[8,9,9,9,0,0,0,1]
 * <p>
 * 提示：
 * 每个链表中的节点数在范围 [1, 100] 内
 * 0 <= Node.val <= 9
 * 题目数据保证列表表示的数字不含前导零
 * <p>
 * https://leetcode.com/problems/add-two-numbers/
 */
public class Problem2_addTwoNumbers {

    // Simulate time: O(m+n) space: O(m+n)
    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode pNode1 = l1;
        ListNode pNode2 = l2;
        ListNode dummyNode = new ListNode(-1);
        ListNode pNode = dummyNode;
        int carry = 0;
        while (pNode1 != null && pNode2 != null) {
            int sum = pNode1.val + pNode2.val + carry;
            carry = 0;
            if (sum >= 10) {
                sum -= 10;
                carry = 1;
            }
            ListNode node = new ListNode(sum);
            pNode.next = node;
            pNode = pNode.next;

            pNode1 = pNode1.next;
            pNode2 = pNode2.next;
        }

        while (pNode1 != null) {
            int sum = pNode1.val + carry;
            carry = 0;
            if (sum >= 10) {
                sum -= 10;
                carry = 1;
            }
            ListNode node = new ListNode(sum);
            pNode.next = node;
            pNode = pNode.next;

            pNode1 = pNode1.next;
        }

        while (pNode2 != null) {
            int sum = pNode2.val + carry;
            carry = 0;
            if (sum >= 10) {
                sum -= 10;
                carry = 1;
            }
            ListNode node = new ListNode(sum);
            pNode.next = node;
            pNode = pNode.next;
            pNode2 = pNode2.next;
        }

        if (carry > 0) {
            ListNode node = new ListNode(carry);
            pNode.next = node;
        }

        return dummyNode.next;
    }

    // Simulate Opt time: O(m+n) space: O(m+n)
    public static ListNode addTwoNumbers_opt(ListNode l1, ListNode l2) {
        ListNode dummyNode = new ListNode(-1);
        ListNode pNode = dummyNode;
        int carry = 0;
        while (l1 != null || l2 != null) {
            int num1 = l1 != null ? l1.val : 0;
            int num2 = l2 != null ? l2.val : 0;
            int sum = num1 + num2 + carry;
            carry = sum / 10;
            pNode.next = new ListNode(sum % 10);
            pNode = pNode.next;

            if (l1 != null) {
                l1 = l1.next;
            }

            if (l2 != null) {
                l2 = l2.next;
            }
        }

        if (carry > 0) {
            pNode.next = new ListNode(carry);
        }

        return dummyNode.next;
    }

    // Recursive time: O(m + n) space: O(max(m, n))
    public static ListNode addTwoNumbers_rec(ListNode l1, ListNode l2) {
        if (l1 == null) {
            return l2;
        } else if (l2 == null) {
            return l1;
        }

        return addNode(l1, l2, 0);
    }

    public static ListNode addNode(ListNode l1, ListNode l2, int carry) {
        if (l1 == null && l2 == null && carry == 0) {
            return null;
        }

        if (l1 != null) {
            carry += l1.val;
            l1 = l1.next;
        }

        if (l2 != null) {
            carry += l2.val;
            l2 = l2.next;
        }

        ListNode addNode = new ListNode(carry % 10);
        carry = carry / 10;
        addNode.next = addNode(l1, l2, carry);
        return addNode;
    }

    public static void main(String[] args) {
        ListNode tst1 = LinkedListNodeUtils.constructListNode(new int[]{2, 4, 3});
        ListNode tst2 = LinkedListNodeUtils.constructListNode(new int[]{5, 6, 4});
        
        System.out.println("[7, 0, 8] ?= " + LinkedListNodeUtils.printLinkedList(addTwoNumbers(tst1, tst2)));
        System.out.println("[7, 0, 8] ?= " + LinkedListNodeUtils.printLinkedList(addTwoNumbers_opt(tst1, tst2)));

        System.out.println("[7, 0, 8] ?= " + LinkedListNodeUtils.printLinkedList(addTwoNumbers_rec(tst1, tst2)));
    }
}
