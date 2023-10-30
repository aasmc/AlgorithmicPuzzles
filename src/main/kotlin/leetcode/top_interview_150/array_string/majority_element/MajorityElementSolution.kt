package leetcode.top_interview_150.array_string.majority_element

class MajorityElementSolution {

    fun majorityElement(nums: IntArray): Int {
        var resIdx = 0
        var cnt = 1
        for (i in 1 until nums.size) {
            if (nums[i] == nums[resIdx]) {
                ++cnt
            } else {
                --cnt
            }
            if (cnt == 0) {
                cnt = 1
                resIdx = i
            }
        }
        cnt = 0
        nums.forEach { n ->
            if (nums[resIdx] == n) {
                ++cnt
            }
        }
        if (cnt > nums.size / 2) {
            return nums[resIdx]
        }
        return -1
    }

}