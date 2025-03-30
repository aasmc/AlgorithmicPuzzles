package leetcode.medium.backtracking.permutations2

class Permutations2Solution {

    fun permuteUnique(nums: IntArray): List<List<Int>> {
        val numToCount = hashMapOf<Int, Int>()
        nums.forEach { num ->
            numToCount.merge(num, 1, Int::plus)
        }
        val result = mutableListOf<List<Int>>()
        fun backTrack(permutation: List<Int>) {
            if (permutation.size == nums.size) {
                result.add(permutation)
                return
            }
            for ((num, count) in numToCount) {
                if (count > 0) {
                    // decrement count for current number
                    numToCount.merge(num, -1, Int::plus)
                    backTrack(permutation + num)
                    // restore count for current value
                    numToCount.merge(num, 1, Int::plus)
                }
            }
        }
        backTrack(listOf())
        return result
    }

}