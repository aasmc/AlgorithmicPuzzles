package leetcode.ace_coding.heap_priority.k_th_largest

import java.util.PriorityQueue

class KThLargestSolution {

    fun findKthLargest(nums: IntArray, k: Int): Int {
        val heap = PriorityQueue<Int>()
        for (i in 0 until k) {
            heap.offer(nums[i])
        }
        for (i in k until nums.size) {
            if (nums[i] > heap.peek()) {
                heap.poll()
                heap.offer(nums[i])
            }
        }
        return heap.peek()
    }

    private fun findKLargestWithQuickSelect(nums: IntArray, k: Int): Int {
        val kIdx = nums.size - k
        fun quickSelect(left: Int, right: Int): Int {
            var pIdx = left
            val pivot = nums[right]
            for (i in left until right) {
                if (nums[i] <= pivot) {
                    val tmp = nums[i]
                    nums[i] = nums[pIdx]
                    nums[pIdx] = tmp
                    ++pIdx
                }
            }
            val tmp = nums[pIdx]
            nums[pIdx] = nums[right]
            nums[right] = tmp

            return if (kIdx < pIdx) {
                quickSelect(left, pIdx - 1)
            } else if (kIdx > pIdx) {
                quickSelect(pIdx + 1, right)
            } else {
                nums[pIdx]
            }
        }
        return quickSelect(0, nums.lastIndex)
    }

}