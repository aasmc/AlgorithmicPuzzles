package leetcode.medium.yandex_prep.add_two_numbers

class AddTwoNumbersSolution {

    class ListNode(var `val`: Int) {
        var next: ListNode? = null
    }

    fun addTwoNumbers(l1: ListNode?, l2: ListNode?): ListNode? {
        if (l1 == null) return l2
        if (l2 == null) return l1

        val dummy = ListNode(0)
        var carry = 0
        var current = dummy
        var l1Cur = l1
        var l2Cur = l2
        while (l1Cur != null || l2Cur != null || carry != 0) {
            val v1 = l1Cur?.`val` ?: 0
            val v2 = l2Cur?.`val` ?: 0
            var value = v1 + v2 + carry
            carry = value / 10
            value %= 10
            current.next = ListNode(value)
            current = current.next!!

            l1Cur = l1Cur?.next
            l2Cur = l2Cur?.next
        }
        return dummy.next
    }

    fun addTwoNumbers2(l1: ListNode?, l2: ListNode?): ListNode? {
        if (l1 == null) return l2
        if (l2 == null) return l1

        fun addWithCarry(node: ListNode, newNode: ListNode, carry: Int) {
            if (carry == 0) {
                var cur: ListNode? = node
                var newCur = newNode
                while (cur != null) {
                    newCur.next = ListNode(cur.`val`)
                    newCur = newCur.next!!
                    cur = cur.next
                }
            } else {
                val sum = node.`val` + carry
                var value = sum
                var newCarry = 0
                if (value >= 10) {
                    value = sum % 10
                    newCarry = sum / 10
                }
                newNode.next = ListNode(value)
                if (node.next == null) {
                    if (newCarry > 0) {
                        newNode.next!!.next = ListNode(newCarry)
                    }
                } else {
                    addWithCarry(node.next!!, newNode.next!!, newCarry)
                }
            }
        }

        fun add(l: ListNode, r: ListNode, carry: Int): ListNode {
            val sum = l.`val` + r.`val` + carry
            var value = sum
            var newCarry = 0
            if (sum >= 10) {
                value = sum % 10
                newCarry = sum / 10
            }
            val new = ListNode(value)
            if (l.next == null && r.next == null) {
                if (newCarry > 0) {
                    new.next = ListNode(newCarry)
                }
            } else if (l.next != null && r.next == null) {
                addWithCarry(l.next!!, new, newCarry)
            } else if (l.next == null && r.next != null) {
                addWithCarry(r.next!!, new, newCarry)
            } else {
                new.next = add(l.next!!, r.next!!, newCarry)
            }
            return new
        }

        return add(l1, l2, 0)
    }

}