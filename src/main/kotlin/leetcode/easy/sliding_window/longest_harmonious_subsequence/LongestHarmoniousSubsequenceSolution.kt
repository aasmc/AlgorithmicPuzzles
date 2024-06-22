package leetcode.easy.sliding_window.longest_harmonious_subsequence

class LongestHarmoniousSubsequenceSolution {

    fun findLHS(nums: IntArray): Int {
        val numToCount = hashMapOf<Int, Int>()
        nums.forEach { num -> numToCount.merge(num, 1, Int::plus) }

        var result = 0
        numToCount.forEach { (num, frequency) ->
            if (numToCount.containsKey(num + 1)) {
                result = maxOf(result, frequency + numToCount[num + 1]!!)
            }
        }
        return result
    }

}