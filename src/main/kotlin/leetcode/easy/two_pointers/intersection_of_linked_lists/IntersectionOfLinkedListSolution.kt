package leetcode.easy.two_pointers.intersection_of_linked_lists

class IntersectionOfLinkedListSolution {

    class ListNode(var `val`: Int) {
        var next: ListNode? = null
    }

    fun getIntersectionNode(headA:ListNode?, headB:ListNode?):ListNode? {
        var curA = headA
        var curB = headB
        if (curA == null || curB == null) {
            return null
        }
        val seen = hashSetOf<ListNode>()
        while (curA != null) {
            seen.add(curA)
            curA = curA.next
        }
        while (curB != null) {
            if (seen.contains(curB)) {
                return curB
            }
            curB = curB.next
        }
        return null
    }
}