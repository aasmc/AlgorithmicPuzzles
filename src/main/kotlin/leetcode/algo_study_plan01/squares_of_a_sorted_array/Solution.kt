package leetcode.algo_study_plan01.squares_of_a_sorted_array

fun sortedSquares(nums: IntArray): IntArray {
    var start = 0
    var end = nums.lastIndex
    val result = IntArray(nums.size)
    for (i in result.lastIndex downTo 0) {
        if (kotlin.math.abs(nums[start]) > kotlin.math.abs(nums[end])) {
            result[i] = nums[start] * nums[start]
            ++start
        } else {
            result[i] = nums[end] * nums[end]
            --end;
        }
    }
    return result
}