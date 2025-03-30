package yandex_algo_training.year_2022.trees

fun <T : Comparable<T>> find(memStruct: MemStruct<T>, root: Int, target: T): Int {
    val memory = memStruct.memory
    val key = memory[root].key ?: return -1
    if (target == key) {
        return root
    } else if (target < key) {
        val left = memory[root].left
        return if (left == -1) {
            -1
        } else {
            find(memStruct, left, target)
        }
    } else {
        val right = memory[root].right
        return if (right == -1) {
            -1
        } else {
            find(memStruct, right, target)
        }
    }
}

private fun <T : Comparable<T>> createAndFillNode(memStruct: MemStruct<T>, key: T): Int {
    val idx = newNode(memStruct)
    val node = memStruct.memory[idx]
    node.key = key
    node.right = -1
    node.left = -1
    return idx
}

fun <T : Comparable<T>> add(memStruct: MemStruct<T>, root: Int, target: T) {
    val key = memStruct.memory[root].key ?: throw IllegalStateException(
        "Tree is not correctly configured. Expected value at index: $root, but was null!"
    )
    if (target < key) {
        val left = memStruct.memory[root].left
        if (left == -1) {
            memStruct.memory[root].left = createAndFillNode(memStruct, target)
        } else {
            add(memStruct, left, target)
        }
    } else if (target > key) {
        val right = memStruct.memory[root].right
        if (right == -1) {
            memStruct.memory[root].right = createAndFillNode(memStruct, target)
        } else {
            add(memStruct, right, target)
        }
    }
}


fun main() {
    val memStruct = initMemory<Int>(20)
    val root = createAndFillNode(memStruct, 8)
    add(memStruct, root, 10)
    add(memStruct, root, 9)
    add(memStruct, root, 14)
    add(memStruct, root, 13)
    add(memStruct, root, 3)
    add(memStruct, root, 1)
    add(memStruct, root, 6)
    add(memStruct, root, 4)
    add(memStruct, root, 7)
    println(memStruct)
}
