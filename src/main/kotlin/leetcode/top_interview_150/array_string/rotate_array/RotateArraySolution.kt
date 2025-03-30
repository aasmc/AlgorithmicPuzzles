package leetcode.top_interview_150.array_string.rotate_array

class RotateArraySolution {

    fun rotate(nums: IntArray, k: Int): Unit {
        if (k == nums.size) return
        val boundary = nums.lastIndex - (k % nums.size)
        reverse(nums, 0, boundary)
        reverse(nums, boundary + 1, nums.lastIndex)
        reverse(nums, 0, nums.lastIndex)
    }

    private fun reverse(nums: IntArray, from: Int, to: Int) {
        if (from >= to) return
        var end = to

        for (i in from..(from + (to - from) / 2)) {
            val tmp = nums[i]
            nums[i] = nums[end]
            nums[end--] = tmp
        }
    }

}