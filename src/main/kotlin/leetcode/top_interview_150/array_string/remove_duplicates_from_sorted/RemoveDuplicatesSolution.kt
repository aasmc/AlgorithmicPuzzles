package leetcode.top_interview_150.array_string.remove_duplicates_from_sorted

class RemoveDuplicatesSolution {

    fun removeDuplicates(nums: IntArray): Int {
        var idx = 1
        var cnt = 1
        for (i in 1 until nums.size) {
            if (nums[i] == nums[i - 1]) {
                if (cnt < 2) {
                    nums[idx++] = nums[i]
                }
                ++cnt
            } else {
                cnt = 1
                nums[idx++] = nums[i]
            }
        }
        return idx
    }


}