package leetcode.top_interview_150.linked_list.merge_sorted_lists

class MergeSortedListsSolution {

    class ListNode(var `val`: Int) {
        var next: ListNode? = null
    }

    fun mergeTwoLists(list1: ListNode?, list2: ListNode?): ListNode? {
        if (list1 == null) return list2
        if (list2 == null) return list1
        var headOne = list1
        var headTwo = list2
        val result: ListNode
        if (headOne.`val` <= headTwo.`val`) {
            result = headOne
            headOne = headOne.next
        } else {
            result = headTwo
            headTwo = headTwo.next
        }
        var current: ListNode? = result
        while (headOne != null && headTwo != null) {
            if (headOne.`val` <= headTwo.`val`) {
                current?.next = headOne
                headOne = headOne.next
            } else {
                current?.next = headTwo
                headTwo = headTwo.next
            }
            current = current?.next
        }

        while (headOne != null) {
            current?.next = headOne
            headOne = headOne.next
            current = current?.next
        }

        while (headTwo != null) {
            current?.next = headTwo
            headTwo = headTwo.next
            current = current?.next
        }
        return result
    }

}