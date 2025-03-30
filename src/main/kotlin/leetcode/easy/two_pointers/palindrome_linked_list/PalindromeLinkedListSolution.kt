package leetcode.easy.two_pointers.palindrome_linked_list

class PalindromeLinkedListSolution {

    class ListNode(var `val`: Int) {
        var next: ListNode? = null
    }

    fun isPalindrome(head: ListNode?): Boolean {
        if (head == null) return false
        var fast = head
        var slow = head
        // slow pointer will be in the middle of the linked list
        while (fast?.next != null) {
            slow = slow?.next
            fast = fast.next?.next
        }
        // now we need to reverse the second half of the linked list
        var prevNode: ListNode? = null
        while (slow != null) {
            val tmp = slow.next
            slow.next = prevNode
            prevNode = slow
            slow = tmp
        }

        // check for palindrome
        var left = head
        // currently prevNode is actually the head of the reversed half of the list
        // we can move it until it becomes null, which means we have reached
        // the middle of the list
        var right = prevNode
        while (right != null) {
            if (left?.`val` != right.`val`) {
                return false
            }
            left = left.next
            right = right.next
        }
        return true
    }

}