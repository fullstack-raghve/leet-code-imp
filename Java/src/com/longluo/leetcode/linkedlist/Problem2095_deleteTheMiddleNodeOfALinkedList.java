package com.longluo.leetcode.linkedlist;

import com.longluo.datastructure.LinkedListNodeUtils;
import com.longluo.datastructure.ListNode;
import com.longluo.datastructure.TreeNode;
import com.longluo.datastructure.TreeUtils;

/**
 * 2095. 删除链表的中间节点
 * <p>
 * 给你一个链表的头节点 head 。删除 链表的 中间节点 ，并返回修改后的链表的头节点 head 。
 * <p>
 * 长度为 n 链表的中间节点是从头数起第 ⌊n / 2⌋ 个节点（下标从 0 开始），其中 ⌊x⌋ 表示小于或等于 x 的最大整数。
 * <p>
 * 对于 n = 1、2、3、4 和 5 的情况，中间节点的下标分别是 0、1、1、2 和 2 。
 * <p>
 * 示例 1：
 * 输入：head = [1,3,4,7,1,2,6]
 * 输出：[1,3,4,1,2,6]
 * 解释：
 * 上图表示给出的链表。节点的下标分别标注在每个节点的下方。
 * 由于 n = 7 ，值为 7 的节点 3 是中间节点，用红色标注。
 * 返回结果为移除节点后的新链表。
 * <p>
 * 示例 2：
 * 输入：head = [1,2,3,4]
 * 输出：[1,2,4]
 * 解释：
 * 上图表示给出的链表。
 * 对于 n = 4 ，值为 3 的节点 2 是中间节点，用红色标注。
 * <p>
 * 示例 3：
 * 输入：head = [2,1]
 * 输出：[2]
 * 解释：
 * 上图表示给出的链表。
 * 对于 n = 2 ，值为 1 的节点 1 是中间节点，用红色标注。
 * 值为 2 的节点 0 是移除节点 1 后剩下的唯一一个节点。
 * <p>
 * 提示：
 * 链表中节点的数目在范围 [1, 10^5] 内
 * 1 <= Node.val <= 10^5
 * <p>
 * https://leetcode.com/problems/delete-the-middle-node-of-a-linked-list/
 */
public class Problem2095_deleteTheMiddleNodeOfALinkedList {

    // Simulate time: O(n) space: O(1)
    public static ListNode deleteMiddle_bf(ListNode head) {
        if (head == null || head.next == null) {
            return null;
        }

        int cnt = 0;
        ListNode pNode = head;
        while (pNode != null) {
            pNode = pNode.next;
            cnt++;
        }

        pNode = head;
        int steps = cnt / 2 - 1;
        while (steps > 0) {
            pNode = pNode.next;
            steps--;
        }

        pNode.next = pNode.next.next;

        return head;
    }

    // Slow Fast Pointers
    public static ListNode deleteMiddle(ListNode head) {
        if (head == null || head.next == null) {
            return null;
        }

        ListNode fast = head;
        ListNode slow = head;

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        slow.next = slow.next.next;

        return head;
    }

    public static void main(String[] args) {
        ListNode tst1 = LinkedListNodeUtils.constructListNode(new int[]{2, 1});
        LinkedListNodeUtils.printLinkedList(tst1);
        LinkedListNodeUtils.printLinkedList(deleteMiddle_bf(tst1));
        LinkedListNodeUtils.printLinkedList(deleteMiddle(tst1));

        ListNode tst2 = LinkedListNodeUtils.constructListNode(new int[]{1, 2, 3, 4});
        LinkedListNodeUtils.printLinkedList(tst2);
        LinkedListNodeUtils.printLinkedList(deleteMiddle(tst2));

        ListNode tst3 = LinkedListNodeUtils.constructListNode(new int[]{1, 3, 4, 7, 1, 2, 6});
        LinkedListNodeUtils.printLinkedList(tst3);
        LinkedListNodeUtils.printLinkedList(deleteMiddle(tst3));
    }
}
