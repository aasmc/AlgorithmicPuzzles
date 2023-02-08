package yandex_algo_training.trees

data class Node<T: Comparable<T>>(
    var key: T? = null,
    var left: Int = -1,
    var right: Int = -1
)

class MemStruct<T: Comparable<T>>(
    val memory: Array<Node<T>>,
    var firstFree: Int
) {
    override fun toString(): String {
        val sb = StringBuilder("MemStruct: [memory=\n")
        memory.forEach { node ->
            sb.append("\t").append(node).append("\n")
        }
        sb.append("firstFreeIndex=$firstFree]")
        return sb.toString()
    }
}


fun <T: Comparable<T>> initMemory(capacity: Int): MemStruct<T> {
    val memory = Array<Node<T>>(capacity) { idx ->
        Node(left = idx + 1)
    }
    val firstFree = 0
    return MemStruct(memory, firstFree)
}

/**
 * Allocates memory for a Node, moves pointer to the next free
 * slot in the memStruct to the next free position.
 * @return pointer to the position of the allocated Node.
 */
fun <T: Comparable<T>> newNode(memStruct: MemStruct<T>): Int {
    val memory = memStruct.memory
    val firstFree = memStruct.firstFree
    memStruct.firstFree = memory[firstFree].left
    return firstFree
}

/**
 * Deletes memory at the specified idx. Moves pointer
 * to the nextFree slot to this index.
 */
fun <T: Comparable<T>> deleteNode(memStruct: MemStruct<T>, idx: Int) {
    val memory = memStruct.memory
    val firstFree = memStruct.firstFree
    memory[idx].left = firstFree
    memory[idx].key = null
    memStruct.firstFree = idx
}
