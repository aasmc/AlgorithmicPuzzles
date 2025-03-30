package leetcode.medium.yandex_prep.flatten_nested_list_iterator

import java.util.LinkedList

class NestedInteger {
    // Constructor initializes an empty nested list.
    constructor()

    // Constructor initializes a single integer.
    constructor(value: Int)

    // @return true if this NestedInteger holds a single integer, rather than a nested list.
    fun isInteger(): Boolean = true

    // @return the single integer that this NestedInteger holds, if it holds a single integer
    // Return null if this NestedInteger holds a nested list
    fun getInteger(): Int? = null

    // Set this NestedInteger to hold a single integer.
    fun setInteger(value: Int): Unit {}

    // Set this NestedInteger to hold a nested list and adds a nested integer to it.
    fun add(ni: NestedInteger): Unit {}

    // @return the nested list that this NestedInteger holds, if it holds a nested list
    // Return null if this NestedInteger holds a single integer
    fun getList(): List<NestedInteger>? = emptyList()
}

class NestedIterator(nestedList: List<NestedInteger>) {

    private val queue = LinkedList<Int>()

    init {
        processNestedList(nestedList)
    }

    private fun processNestedList(lst: List<NestedInteger>) {
        lst.forEach(::processOneNestedList)
    }

    private fun processOneNestedList(lst: NestedInteger) {
        if (lst.isInteger()) {
            queue.addLast(lst.getInteger()!!)
        } else if (lst.getList() != null) {
            processNestedList(lst.getList()!!)
        }
    }

    fun next(): Int {
        return queue.removeFirst()
    }

    fun hasNext(): Boolean = queue.isNotEmpty()
}