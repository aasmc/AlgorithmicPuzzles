package leetcode.top_interview_150.linked_list.add_two_numbers

class AddTwoNumbersSolution {

    class ListNode(var `val`: Int) {
        var next: ListNode? = null
    }

    fun addTwoNumbers(l1: ListNode?, l2: ListNode?): ListNode? {
        if (l1 == null) return l2
        if (l2 == null) return l1
        var overflow = 0
        var left = l1
        var right = l2
        while (left != null && right != null) {
            left.`val` += right.`val`
            left.`val` += overflow
            overflow = 0
            if (left.`val` >= 10) {
                overflow = 1
                left.`val` %= 10
            }
            if (left.next == null && right.next != null) {
                left.next = right.next
                left = left.next
                break
            }
            if (left.next == null && right.next == null && overflow != 0) {
                left.next = ListNode(overflow)
                return l1
            } else {
                left = left.next
                right = right.next
            }
        }

        if (left != null) {
            left.`val` += overflow
            while (left!!.`val` >= 10) {
                overflow = 1
                left.`val` %= 10
                if (left.next == null) {
                    left.next = ListNode(overflow)
                } else {
                    left.next!!.`val` += overflow
                }
                left = left.next
            }
        }

        return l1
    }

}