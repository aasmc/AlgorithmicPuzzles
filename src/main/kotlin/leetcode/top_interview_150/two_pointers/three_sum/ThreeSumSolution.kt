package leetcode.top_interview_150.two_pointers.three_sum

class ThreeSumSolution {

    fun threeSum(nums: IntArray): List<List<Int>> {
        val result = mutableListOf<List<Int>>()
        // sort the input array to correctly handle duplicates
        nums.sort()
        // traverse the sorted array and take each element as the first
        // element in the three sum
        for((idx, num) in nums.withIndex()) {
            // if we start from the same element, then we either found
            // a three sum already, or there's no three sum with this element
            // as the first one, so skip it
            if (idx > 0 && num == nums[idx - 1]) {
                continue
            }
            // check other elements with a simple two sum algorithm
            var left = idx + 1
            var right = nums.lastIndex
            while (left < right) {
                val threeSum = num + nums[left] + nums[right]
                if (threeSum > 0) {
                    --right
                } else if (threeSum < 0) {
                    ++left
                } else {
                    result.add(listOf(num, nums[left], nums[right]))
                    ++left
                    while (nums[left] == nums[left - 1] && left < right) {
                        ++left
                    }
                }
            }
        }
        return result
    }

}