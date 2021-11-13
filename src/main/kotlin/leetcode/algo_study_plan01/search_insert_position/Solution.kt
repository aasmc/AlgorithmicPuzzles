package leetcode.algo_study_plan01.search_insert_position

fun searchInsert(nums: IntArray, target: Int): Int {
    var start = 0
    var end = nums.lastIndex
    while (start <= end) {
        val middle = start + ((end - start) shr 1)
        if (nums[middle] == target) {
            return middle
        }
        if (nums[middle] > target) {
            end = middle -1
        } else {
            start = middle + 1
        }
    }
    return end + 1
}