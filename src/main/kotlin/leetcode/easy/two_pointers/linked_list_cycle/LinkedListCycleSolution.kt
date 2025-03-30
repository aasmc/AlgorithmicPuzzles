package leetcode.easy.two_pointers.linked_list_cycle

class LinkedListCycleSolution {

    class ListNode(var `val`: Int) {
        var next: ListNode? = null
    }


    fun hasCycle(head: ListNode?): Boolean {
        if (head == null) return false
        var slow = head
        var fast = slow

        while (fast?.next != null) {
            slow = slow?.next
            fast = fast.next?.next
            if (slow == fast) {
                return true
            }
        }
        return false
    }
}