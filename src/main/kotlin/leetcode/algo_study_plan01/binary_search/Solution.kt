package leetcode.algo_study_plan01.binary_search

fun search(nums: IntArray, target: Int): Int {
    if (nums.isEmpty()) {
        return -1
    }
    var start = 0
    var end = nums.lastIndex
    while (start <= end) {
        val middle = start + (end - start) / 2
        if (target == nums[middle]) {
            return middle
        }
        if (target < nums[middle]) {
            end = middle - 1
        } else {
            start = middle + 1
        }
    }
    return -1
}