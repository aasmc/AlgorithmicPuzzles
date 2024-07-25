package leetcode.medium.backtracking.subsets

class SubsetsSolution {

    fun subsets(nums: IntArray): List<List<Int>> {
        if (nums.isEmpty()) {
            return listOf(emptyList())
        }
        val result = mutableListOf<List<Int>>()
        fun backTrack(idx: Int, subset: List<Int>) {
            if (idx == nums.size) {
                result.add(subset)
                return
            }
            // decision to include nums[i]
            backTrack(idx + 1, subset + nums[idx])
            // decision not to include nums[i]
            backTrack(idx + 1, subset)
        }
        backTrack(0, listOf())
        return result
    }

}