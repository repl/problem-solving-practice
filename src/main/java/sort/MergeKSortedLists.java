package sort;

import java.util.Comparator;
import java.util.PriorityQueue;

public class MergeKSortedLists {
    /*
    LeetCode 23. Merge k Sorted Lists
    Merge k sorted linked lists and return it as one sorted list. Analyze and describe its complexity.

    Example:
    Input:
    [
      1->4->5,
      1->3->4,
      2->6
    ]
    Output: 1->1->2->3->4->4->5->6

     */
    static class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x;}

    }

    public static void main(String[] args) {
        ListNode firstList = new ListNode(1);
        firstList.next = new ListNode(4);
        firstList.next.next = new ListNode(5);
        ListNode secondList = new ListNode(1);
        secondList.next = new ListNode(3);
        secondList.next.next = new ListNode(4);
        ListNode thirdList = new ListNode(2);
        thirdList.next = new ListNode(6);
        MergeKSortedLists instance = new MergeKSortedLists();
        ListNode[] input = {firstList, secondList, thirdList};
        traverse(instance.mergeKLists(input));
    }

    private static void traverse(ListNode n) {
        while (n != null) {
            System.out.print(n.val + ((n.next != null)?"->":""));
            n = n .next;
        }
        System.out.println();
    }

    public ListNode mergeKLists(ListNode[] lists) {
        if (lists.length == 0) {
            return null;
        }
        ListNode traverser = null, result = null;
        PriorityQueue<ListNode> minHeap = new PriorityQueue<>(lists.length, new Comparator<ListNode>() {
            @Override
            public int compare(ListNode o1, ListNode o2) {
                return o1.val - o2.val;
            }
        });
        for (int i = 0;i < lists.length; i++) {
            if (lists[i] != null) {
                minHeap.add(lists[i]);
            }
        }
        while (!minHeap.isEmpty()) {
            ListNode elem = minHeap.poll();
            if (traverser == null) {
                traverser = new ListNode(elem.val);
                result = traverser;
            } else {
                traverser.next = new ListNode(elem.val);
                traverser = traverser.next;
            }
            if (elem.next != null) {
                minHeap.add(elem.next);
            }
        }
        return result;
    }
}
