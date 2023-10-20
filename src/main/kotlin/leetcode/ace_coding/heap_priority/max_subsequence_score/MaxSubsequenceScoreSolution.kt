package leetcode.ace_coding.heap_priority.max_subsequence_score

import java.util.PriorityQueue

class MaxSubsequenceScoreSolution {

    fun maxScore(nums1: IntArray, nums2: IntArray, k: Int): Long {
        // it will store pairs of numbers such that first in the pair
        // is element from nums2 array, and second in the pair is element
        // from nums1 array. But the list of pairs will be sorted in descending
        // order by the elements from nums2 array. This property is needed
        // because we want to maximize the minimum of k elements from nums2 (it is
        // because we want to multiply it my the maximum sum of k elements from nums1 array,
        // this will allow us to get the answer)
        val sorted = mutableListOf<Pair<Int, Int>>()
        for (i in nums1.indices) {
            sorted.add(Pair(nums2[i], nums1[i]))
        }
        sorted.sortByDescending { it.first }

        // it will store the maximum sum of k elements from nums1 array
        val heap = PriorityQueue<Int>()
        var currentSum = 0L
        for (i in 0 until k) {
            currentSum += sorted[i].second
            heap.offer(sorted[i].second)
        }
        var result = currentSum * sorted[k - 1].first
        for (i in k until nums1.size) {
            currentSum -= heap.poll()
            currentSum += sorted[i].second
            heap.offer(sorted[i].second)
            result = maxOf(result, currentSum * sorted[i].first)
        }
        return result
    }

}