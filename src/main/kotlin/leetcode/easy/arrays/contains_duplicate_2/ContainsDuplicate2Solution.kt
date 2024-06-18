package leetcode.easy.arrays.contains_duplicate_2

import kotlin.math.abs

class ContainsDuplicate2Solution {

    fun containsNearbyDuplicate(nums: IntArray, k: Int): Boolean {
        val numToIdx = hashMapOf<Int, Int>()
        nums.forEachIndexed { index, num ->
            numToIdx[num]?.let { prevIdx ->
                if (abs(prevIdx - index) <= k) {
                    return true
                }
            }
            numToIdx[num] = index
        }
        return false
    }

}