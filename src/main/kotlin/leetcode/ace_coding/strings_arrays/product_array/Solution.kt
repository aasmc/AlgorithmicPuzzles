package leetcode.ace_coding.strings_arrays.product_array

fun productExceptSelf(nums: IntArray): IntArray {
    val result = IntArray(nums.size) { 0 }
    var prefix = 1
    for (i in result.indices) {
        result[i] = prefix
        prefix *= nums[i]
    }
    var postfix = 1
    for (i in result.lastIndex downTo 0) {
        result[i] *= postfix
        postfix *= nums[i]
    }
    return result
}