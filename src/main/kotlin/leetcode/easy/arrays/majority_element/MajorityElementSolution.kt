package leetcode.easy.arrays.majority_element

class MajorityElementSolution {

    fun majorityElement(nums: IntArray): Int {
        var count = 1
        var elem = nums[0]
        for (i in 1 until nums.size) {
            if (count == 0) {
                elem = nums[i]
            }
            if (elem == nums[i]) {
                ++count
            } else {
                --count
            }
        }
        return elem
    }

}