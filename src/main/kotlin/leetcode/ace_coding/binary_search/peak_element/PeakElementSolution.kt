package leetcode.ace_coding.binary_search.peak_element

class PeakElementSolution {

    fun findPeakElement(nums: IntArray): Int {
        var start = 0
        var end = nums.lastIndex
        while (start <= end) {
            val mid = start + (end - start) / 2
            if ((mid == 0 || nums[mid - 1] < nums[mid]) &&
                (mid == nums.lastIndex || nums[mid] > nums[mid + 1])) {
                return mid
            }
            if (mid > 0 && nums[mid - 1] > nums[mid]) {
                end = mid - 1
            } else {
                start = mid + 1
            }
        }
        return -1
    }

}