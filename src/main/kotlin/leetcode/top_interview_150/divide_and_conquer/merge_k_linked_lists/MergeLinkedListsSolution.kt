package leetcode.top_interview_150.divide_and_conquer.merge_k_linked_lists

import leetcode.top_interview_150.divide_and_conquer.sort_list.SortListSolution

class MergeLinkedListsSolution {
    class ListNode(var `val`: Int) {
        var next: ListNode? = null
    }

    fun mergeKLists(lists: Array<ListNode?>): ListNode? {
        if (lists.isEmpty()) {
            return null
        }
        if (lists.size == 1) {
            return lists[0]
        }
        return mergeKLists(lists, 0, lists.size)
    }

    private fun mergeKLists(lists: Array<ListNode?>, from: Int, to: Int): ListNode? {
        if (to - from == 1) {
            return lists[from]
        }
        val mid = from + (to - from) / 2
        val left = mergeKLists(lists, from, mid)
        val right = mergeKLists(lists, mid, to)
        return merge(left, right)
    }

    private fun merge(left: ListNode?, right: ListNode?): ListNode? {
        val preHead = ListNode(-1)
        var tail: ListNode? = preHead
        var l = left
        var r = right
        while (l != null && r != null) {
            if (l.`val` <= r.`val`) {
                tail!!.next = l
                l = l.next
            } else {
                tail!!.next = r
                r = r.next
            }
            tail = tail.next
        }
        if (l != null) {
            tail!!.next = l
        }
        if (r != null) {
            tail!!.next = r
        }
        return preHead.next
    }

}