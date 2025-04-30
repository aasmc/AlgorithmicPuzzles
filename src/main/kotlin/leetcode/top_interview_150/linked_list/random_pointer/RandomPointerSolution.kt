package leetcode.top_interview_150.linked_list.random_pointer

class RandomPointerSolution {

    class Node(var `val`: Int) {
        var next: Node? = null
        var random: Node? = null
    }

    fun copyRandomList(node: Node?): Node? {
        if (node == null) return null
        val originalToNew = hashMapOf<Node, Node>()
        var current = node
        while (current != null) {
            originalToNew[current] = Node(current.`val`)
            current = current.next
        }
        current = node
        while (current != null) {
            val newNode = originalToNew[current]!!
            newNode.next = originalToNew[current.next]
            current.random?.let {
                newNode.random = originalToNew[it]
            }
            current = current.next
        }
        return originalToNew[node]
    }

}