package leetcode.top_interview_150.linked_list.linked_list_cycle

class LinkedListCycleSolution {

    class ListNode(var `val`: Int) {
        var next: ListNode? = null
    }

    fun hasCycle(head: ListNode?): Boolean {
        if(head?.next == null) return false
        var slow = head.next
        var fast = head.next?.next
        while(fast != null) {
            if (fast == slow) {
                return true
            }
            slow = slow?.next
            fast = fast.next?.next
        }
        return false
    }

}