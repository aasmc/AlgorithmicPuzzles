package leetcode.easy.yandex_prep.merge_sorted_lists

class MergeSortedListsSolution {

    class ListNode(var `val`: Int) {
        var next: ListNode? = null
    }

    fun mergeTwoLists(list1: ListNode?, list2: ListNode?): ListNode? {
        if (list1 == null) return list2
        if (list2 == null) return list1
        var headOne = list1
        var headTwo = list2
        var head: ListNode? = null
        if (headOne.`val` <= headTwo.`val`) {
            head = headOne
            headOne = headOne.next
        } else {
            head = headTwo
            headTwo = headTwo.next
        }
        var current = head
        while (headOne != null && headTwo != null) {
            if (headOne.`val` <= headTwo.`val`) {
                current!!.next = headOne
                headOne = headOne.next
            } else {
                current!!.next = headTwo
                headTwo = headTwo.next
            }
            current = current.next
        }
        while (headOne != null) {
            current!!.next = headOne
            headOne = headOne.next
            current = current.next
        }

        while (headTwo != null) {
            current!!.next = headTwo
            headTwo = headTwo.next
            current = current.next
        }

        return head
    }

}