package leetcode.easy.arrays.two_sum

class TwoSumSolution {

    fun twoSum(nums: IntArray, target: Int): IntArray {
        val valueToIdx = hashMapOf<Int, Int>()
        nums.forEachIndexed { idx, num ->
            if (valueToIdx.containsKey(target - num)) {
                return intArrayOf(valueToIdx[target - num]!!, idx)
            }
            valueToIdx[num] = idx
        }
        throw IllegalArgumentException()
    }

}