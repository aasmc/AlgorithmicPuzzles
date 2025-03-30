package leetcode.medium.backtracking.partition_k_equal_sum

class PartitionKEqualSumSolution {

    fun canPartitionKSubsets(nums: IntArray, k: Int): Boolean {
        val totalSum = nums.sumOf { it }
        if (totalSum % k != 0) return false
        val target = totalSum / k
        val used = BooleanArray(nums.size)
        // Time Complexity: O(k * 2^n)
        fun dfs(idx: Int, subsetsLeft: Int, subsetSum: Int): Boolean {
            if (subsetsLeft == 0) {
                return true
            }
            if (subsetSum == target) {
                return dfs(0, subsetsLeft - 1, 0)
            }
            for (j in idx until nums.size) {
                if (used[j] || subsetSum + nums[j] > target) {
                    continue
                }
                used[j] = true
                if (dfs(j + 1, subsetsLeft, subsetSum + nums[j])) {
                    return true
                }
                used[j] = false
            }
            return false
        }
        return dfs(0, k, 0)
    }

}