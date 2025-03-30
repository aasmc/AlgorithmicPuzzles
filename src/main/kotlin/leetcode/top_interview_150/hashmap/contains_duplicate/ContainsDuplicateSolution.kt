package leetcode.top_interview_150.hashmap.contains_duplicate

import kotlin.math.abs

class ContainsDuplicateSolution {

    fun containsNearbyDuplicate(nums: IntArray, k: Int): Boolean {
        val numToIndex = hashMapOf<Int, Int>()
        nums.forEachIndexed { index, num ->
            numToIndex[num]?.let { prevIdx ->
                if (abs(prevIdx - index) <= k) return true
            }
            numToIndex[num] = index
        }
        return false
    }

}