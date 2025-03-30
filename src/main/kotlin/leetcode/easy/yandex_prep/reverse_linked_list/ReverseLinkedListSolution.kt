package leetcode.easy.yandex_prep.reverse_linked_list

class ReverseLinkedListSolution {

    class ListNode(var `val`: Int) {
        var next: ListNode? = null
    }

    fun reverseList(head: ListNode?): ListNode? {
        if (head == null) return null
        var previous: ListNode? = null
        var current = head
        while (current != null) {
            val tmp = current.next
            current.next = previous
            previous = current
            current = tmp
        }
        return previous
    }

}