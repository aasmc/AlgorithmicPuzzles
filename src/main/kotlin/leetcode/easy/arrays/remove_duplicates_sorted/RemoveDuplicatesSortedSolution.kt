package leetcode.easy.arrays.remove_duplicates_sorted

class RemoveDuplicatesSortedSolution {

    fun removeDuplicates(nums: IntArray): Int {
        var idx = 0
        for (i in 1 until nums.size) {
            if (nums[idx] != nums[i]) {
                nums[++idx] = nums[i]
            }
        }
        return idx + 1
    }

}