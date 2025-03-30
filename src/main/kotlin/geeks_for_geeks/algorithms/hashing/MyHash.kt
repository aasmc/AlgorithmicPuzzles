package geeks_for_geeks.algorithms.hashing

import java.lang.IllegalArgumentException

/**
 * Simple implementation of bound hash table that uses linear probing for resolving
 * collisions during hashing. In this simplified version user cannot insert -1 or -2 into
 * the table.
 */
class MyHash(private val capacity: Int) {

    private val internalArray = IntArray(capacity) { ABSENT_VALUE }
    private var _size: Int = 0
    val size: Int
        get() = _size

    fun search(target: Int): Boolean {
        val index = findIndex(target)
        var i = index
        while (internalArray[i] != ABSENT_VALUE) {
            if (internalArray[i] == target) {
                return true
            }
            i = (i + 1) % capacity
            if (i == index) return false
        }
        return false
    }

    fun insert(number: Int) {
        if (_size == capacity) throw IllegalStateException("MyHash is full. You cannot insert additional element.")
        val index = findIndex(number)
        var i = index
        while (internalArray[i] != ABSENT_VALUE && internalArray[i] != ERASED_VALUE) {
            i = (i + 1) % capacity
        }
        internalArray[i] = number
        ++_size
    }

    fun erase(number: Int) {
        val index = findIndex(number)
        var i = index
        while (internalArray[i] != number) {
            i = (i + 1) % capacity
            if (i == index || internalArray[i] == ABSENT_VALUE) {
                throw IllegalArgumentException("Sorry. No such value: $number in MyHash")
            }
        }
        internalArray[i] = ERASED_VALUE
        --_size
    }

    fun print() {
        println(internalArray.contentToString())
    }

    private fun findIndex(number: Int): Int {
        return number % capacity
    }

    companion object {
        private const val ABSENT_VALUE = -1
        private const val ERASED_VALUE = -2
    }
}