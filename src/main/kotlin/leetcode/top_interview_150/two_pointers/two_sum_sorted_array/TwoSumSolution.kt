package leetcode.top_interview_150.two_pointers.two_sum_sorted_array

class TwoSumSolution {

    fun twoSum(numbers: IntArray, target: Int): IntArray {
        var left = 0
        var right = numbers.lastIndex
        while (left < right) {
            val sum = numbers[left] + numbers[right]
            if (sum == target) {
                return intArrayOf(left + 1, right + 1)
            }
            if (sum < target) {
                ++left
            }
            if (sum > target) {
                --right
            }
        }
        return intArrayOf(-1, -1)
    }

}