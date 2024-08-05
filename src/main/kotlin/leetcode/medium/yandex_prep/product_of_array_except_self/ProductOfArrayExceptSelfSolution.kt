package leetcode.medium.yandex_prep.product_of_array_except_self

class ProductOfArrayExceptSelfSolution {

    fun productExceptSelf(nums: IntArray): IntArray {
        val result = IntArray(nums.size)
        // This loop ensures that result[i] stores products of all
        // numbers before i
        // e.g. Initial array [1,2,3,4], result = [0,0,0,0]
        // 1. result = [1,0,0,0] prefix = 1
        // 2. result = [1,1,0,0] prefix = 2
        // 3. result = [1,1,2,0] prefix = 6
        // 4. result = [1,1,2,6] prefix = 24 (we don't need this prefix)
        var prefix = 1
        for (i in nums.indices) {
            result[i] = prefix
            prefix *= nums[i]
        }
        // This loop ensures that result[i] stores products of all numbers
        // except i
        // e.g. continuing previous iterations
        // result = [1,1,2,6]
        // 1. result = [1,1,2,6], postfix = 4
        // 2. result = [1,1,8,6], postfix = 12
        // 3. result = [1,12,8,6], postfix = 24
        // 4. result = [1,1,8,6], postfix = 24 (we don't need this postfix)
        var postfix = 1
        for (i in nums.lastIndex downTo 0) {
            result[i] *= postfix
            postfix *= nums[i]
        }
        return result
    }

}