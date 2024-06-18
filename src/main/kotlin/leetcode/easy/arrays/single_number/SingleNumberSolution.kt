package leetcode.easy.arrays.single_number

class SingleNumberSolution {

    fun singleNumber(nums: IntArray): Int {
        var result = nums[0]
        for (i in 1 until nums.size) {
            result = result xor nums[i]
        }
        return result
    }

}