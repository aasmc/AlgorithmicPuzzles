package leetcode.top_interview_150.divide_and_conquer.sort_list

class SortListSolution {

    class ListNode(var `val`: Int) {
        var next: ListNode? = null
    }

    fun sortList(head: ListNode?): ListNode? {
        // base case
        if(head?.next == null) {
            return head
        }
        val mid = getMiddle(head)
        val right = mid?.next
        mid?.next = null

        // sort recursively
        val sortedLeft = sortList(head)
        val sortedRight = sortList(right)

        // merge
        return merge(sortedLeft, sortedRight)
    }

    private fun getMiddle(node: ListNode): ListNode? {
        var turtle: ListNode? = node
        var hare = node.next
        while (hare?.next != null) {
            turtle = turtle?.next
            hare = hare.next?.next
        }
        return turtle
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