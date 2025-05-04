package leetcode.top_interview_150.linked_list.remove_duplicates_sorted_ii

class RemoveDuplicatesSortedListTwoSolution {

    class ListNode(var `val`: Int) {
        var next: ListNode? = null
        override fun toString(): String {
            return "ListNode(value=${`val`}, next=${next?.`val`}"
        }
    }

    fun deleteDuplicates(head: ListNode?): ListNode? {
        if (head == null) return head
        val valueToCountToNode = linkedMapOf<Int, Pair<Int, ListNode>>()
        var cur = head
        while (cur != null) {
            if (valueToCountToNode.containsKey(cur.`val`)) {
                val pair = valueToCountToNode[cur.`val`]!!
                valueToCountToNode[cur.`val`] = pair.first + 1 to pair.second
            } else {
                valueToCountToNode[cur.`val`] = 1 to cur
            }
            cur = cur.next
        }
        val newList = valueToCountToNode.values.toList()
        var newHeadPair: Pair<Int, ListNode>? = null
        var i = 0
        while (i < newList.size) {
            if (newList[i].first == 1) {
                newHeadPair = newList[i]
                ++i
                break
            }
            ++i
        }
        if (newHeadPair == null) return null
        val newHead = newHeadPair.second
        var tmp = newHead
        while (i < newList.size) {
            tmp.next = null
            val curPair = newList[i]
            if (curPair.first == 1) {
                tmp.next = curPair.second
                tmp = curPair.second
            }
            ++i
        }
        return newHead
    }

}