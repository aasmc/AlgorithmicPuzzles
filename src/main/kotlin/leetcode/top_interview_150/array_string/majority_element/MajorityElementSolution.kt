package leetcode.top_interview_150.array_string.majority_element

class MajorityElementSolution {

    fun majorityElement(nums: IntArray): Int {
        var count = 0
        var candidate = nums[0]
        for (i in 1 until nums.size) {
            if (count == 0) {
                candidate = nums[i]
            }
            if (candidate == nums[i]) {
                ++count
            } else {
                --count
            }
        }
        return candidate
    }

}