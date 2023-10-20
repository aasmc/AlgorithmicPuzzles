package leetcode.ace_coding.heap_priority.smallest_in_infinite_set

import java.util.PriorityQueue

class SmallestInfiniteSet() {

    private val heap = PriorityQueue<Int>()
    private var currentElement = 1
    private val unique = hashSetOf<Int>()
    fun popSmallest(): Int {
        if (heap.isEmpty()) return currentElement++
        val min = heap.poll()
        unique.remove(min)
        return min
    }

    fun addBack(num: Int) {
        if (num < currentElement && !unique.contains(num)) {
            heap.offer(num)
            unique.add(num)
        }
    }

}