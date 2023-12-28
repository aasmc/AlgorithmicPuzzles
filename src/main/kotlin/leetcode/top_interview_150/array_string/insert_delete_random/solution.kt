package leetcode.top_interview_150.array_string.insert_delete_random

class RandomizedSet() {

    private val elemToIndex = hashMapOf<Int, Int>()
    private val elements = arrayListOf<Int>()

    fun insert(`val`: Int): Boolean {
        if (!elemToIndex.containsKey(`val`)) {
            elements.add(`val`)
            elemToIndex[`val`] = elements.lastIndex
            return true
        }
        return false
    }

    fun remove(`val`: Int): Boolean {
        if (elemToIndex.containsKey(`val`)) {
            val index = elemToIndex[`val`]!!
            val lastElem = elements.last()
            elements[index] = lastElem
            elements.removeLast()
            elemToIndex[lastElem] = index
            elemToIndex.remove(`val`)
            return true
        }
        return false
    }

    fun getRandom(): Int {
        val idx = (0..elements.lastIndex).random()
        return elements[idx]
    }

}