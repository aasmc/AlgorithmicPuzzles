package leetcode.top_interview_150.array_string.remove_element

class RemoveElementSolution {

    fun removeElement(nums: IntArray, `val`: Int): Int {
        var idx = 0
        for (i in nums.indices) {
            if (nums[i] != `val`) {
                nums[idx++] = nums[i]
            }
        }
        return idx
    }

}