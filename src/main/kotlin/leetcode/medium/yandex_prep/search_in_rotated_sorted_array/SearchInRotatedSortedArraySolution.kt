package leetcode.medium.yandex_prep.search_in_rotated_sorted_array

class SearchInRotatedSortedArraySolution {

    fun search(nums: IntArray, target: Int): Int {
        var left = 0
        var right = nums.lastIndex
        while (left <= right) {
            val mid = left + (right - left)/ 2
            if (nums[mid] == target) return mid
            // we are in the left sorted portion
            if (nums[mid] >= nums[left]) {
                if (target > nums[mid] || target < nums[left]) {
                    left = mid + 1
                } else {
                    right = mid - 1
                }
            } else {
                if (target < nums[mid] || target > nums[right]) {
                    right = mid - 1
                } else {
                    left = mid + 1
                }
            }
        }
        return -1
    }

}