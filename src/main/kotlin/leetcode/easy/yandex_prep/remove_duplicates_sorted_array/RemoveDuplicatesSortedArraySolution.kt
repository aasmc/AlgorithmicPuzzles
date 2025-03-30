package leetcode.easy.yandex_prep.remove_duplicates_sorted_array

class RemoveDuplicatesSortedArraySolution {

    fun removeDuplicates(nums: IntArray): Int {
        var idx = 0
        for (right in 1 until nums.size) {
            if (nums[right] != nums[right - 1]) {
                nums[++idx] = nums[right]
            }
        }
        return idx + 1
    }

}