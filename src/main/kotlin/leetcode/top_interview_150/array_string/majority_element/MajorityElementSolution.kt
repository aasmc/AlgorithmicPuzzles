package leetcode.top_interview_150.array_string.majority_element

class MajorityElementSolution {

    fun majorityElement(nums: IntArray): Int {
        var count = 0
        var candidate = 0
        for (num in nums) {
            if (count == 0) {
                candidate = num
            }
            if (num == candidate) {
                ++count
            } else {
                --count
            }
        }
        return candidate
    }

}