package leetcode.ace_coding.two_pointers.k_sum_pairs

fun maxOperations(nums: IntArray, k: Int): Int {
    nums.sort()
    var start = 0
    var end = nums.lastIndex
    var count = 0
    while (start < end) {
        val sum = nums[start] + nums[end]
        if (sum == k) {
            ++count
            ++start
            --end
        } else if (sum < k) {
            ++start
        } else {
            --end
        }
    }
    return count
}