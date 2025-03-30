package leetcode.top_interview_150.hashmap.two_sum

class TwoSumSolution {

    fun twoSum(nums: IntArray, target: Int): IntArray {
        val numToIndex = hashMapOf<Int, Int>()
        nums.forEachIndexed { index, num ->
            val complement = target - num
            if (numToIndex.containsKey(complement)) {
                return intArrayOf(index, numToIndex[complement]!!)
            }
            numToIndex[num] = index
        }

        return intArrayOf()
    }

}