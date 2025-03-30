package leetcode.top_interview_150.linked_list.lru_cache

class LRUCache(capacity: Int) {

    data class Node(
        val key: Int,
        val value: Int,
        var next: Node? = null,
        var prev: Node? = null
    ) {
        override fun toString(): String {
            return "Node: [key=${key}, value=$value, prev=${prev?.value}, next=${next?.value}]"
        }
    }

    private var head: Node? = null
    private var tail: Node? = null

    private val map = hashMapOf<Int, Node>()

    private var size = 0
    private val cap = capacity

    fun get(key: Int): Int {
        val node = map[key] ?: return -1
        put(node.key, node.value)
        return node.value
    }

    fun put(key: Int, value: Int) {
        emplaceFront(key, value)
        tryRemoveOldNodeByKey(key)
        map[key] = head!!
        if (size > cap) {
            removeTail()
        }
    }

    private fun tryRemoveOldNodeByKey(key: Int) {
        val removed = map.remove(key)
        removed?.let { removeNode(it) }
    }

    private fun removeNode(node: Node) {
        if (node == tail) {
            removeTail()
        } else {
            removeMiddleNode(node)
        }
    }

    private fun removeMiddleNode(node: Node) {
        node.prev?.next = node.next
        node.next?.prev = node.prev
        node.next = null
        node.prev = null
        --size
    }

    private fun removeTail() {
        if (tail != null) {
            map.remove(tail!!.key)
            tail!!.prev?.next = null
            tail = tail!!.prev
            --size
        }
    }

    private fun emplaceFront(key: Int, value: Int) {
        val node = Node(key, value)
        node.next = head
        if (head != null) {
            head!!.prev = node
        } else {
            tail = node
        }
        head = node
        ++size
    }
}