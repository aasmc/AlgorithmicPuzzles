package leetcode.easy.yandex_prep.palindrome_linked_list

class PalindromeLinkedListSolution {

    class ListNode(var `val`: Int) {
        var next: ListNode? = null
    }

    fun isPalindrome(head: ListNode?): Boolean {
        // 1 -> 2 -> 2 -> 1
        if (head == null) return false
        var middle = head
        var last = head
        // move slow to the middle of the list
        // last pointer will either be null or its next pointer will be null
        // if the number of nodes in the list is odd, last pointer will be not null
        // but its next pointer will be null, if the number of nodes in the list
        // is even, last pointer will be null by the end of the cycle
        while (last?.next != null) {
            middle = middle?.next
            last = last.next?.next
        }

        // 1 -> 2 -> 2 -> 1 -> null
        //           |          |
        //          middle     last

        // reverse the right part of the list, that starts with slow pointer
        // step 1: save middle.next to temp variable
        // step 2: point middle.next to previous value
        // step 3: set previous pointer to the middle pointer
        // step 4: move middle pointer to the temp pointer
        // by the end of the cycle, middle pointer will be null, and previous
        // pointer will point to the head of the list from the right side
        var previous: ListNode? = null
        while (middle != null) {
            val tmp = middle.next
            middle.next = previous
            previous = middle
            middle = tmp
        }

        // 1 -> 2 -> 2 <- 1
        //           |    |
        //           |   previous
        //         null

        // compare for palindrome by checking values at left and previous pointer
        // when previous pointer points to null, the right list is empty
        var left = head
        while (previous != null) {
            if (left?.`val` != previous.`val`) {
                return false
            }
            left = left.next
            previous = previous.next
        }
        return true
    }

}