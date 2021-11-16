package leetcode.algo_study_plan01.move_zeroes

fun moveZeroes(nums: IntArray): Unit {
    var zeroIndex = 0
    for (i in nums.indices) {
        if (nums[i] != 0) {
            swap(nums, zeroIndex, i)
            ++zeroIndex
        }
    }
}

fun swap(nums: IntArray, left: Int, right: Int) {
    val tmp = nums[left]
    nums[left] = nums[right]
    nums[right] = tmp
}