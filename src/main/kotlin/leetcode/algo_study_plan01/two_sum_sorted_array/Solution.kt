package leetcode.algo_study_plan01.two_sum_sorted_array

fun twoSum(numbers: IntArray, target: Int): IntArray {
    var left = 0
    var right = numbers.lastIndex
    while (left < right) {
        if (numbers[left]+ numbers[right] ==target) {
            return intArrayOf(left + 1, right + 1)
        }
        if (numbers[left] + numbers[right] < target) {
            ++left
        } else if (numbers[left] + numbers[right] > target) {
            --right
        }
    }
    return intArrayOf(-1, -1)
}