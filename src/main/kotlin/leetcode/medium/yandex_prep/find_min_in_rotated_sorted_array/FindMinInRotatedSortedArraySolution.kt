package leetcode.medium.yandex_prep.find_min_in_rotated_sorted_array

class FindMinInRotatedSortedArraySolution {

    fun findMin(nums: IntArray): Int {
        var left = 0
        var right = nums.lastIndex
        var res = nums[0]

        while (left <= right) {
            if (nums[left] < nums[right]) {
                res = minOf(res, nums[left])
                break
            }
            val mid = left + (right - left) / 2
            res = minOf(res, nums[mid])
            if (nums[mid] >= nums[left]) {
                left = mid + 1
            } else {
                right = mid - 1
            }
        }
        return res
    }

    fun findMin3(nums: IntArray): Int {
        var left = 0
        var right = nums.lastIndex

        while (left < right) {
            val mid = left + (right - left) / 2
            if (mid != 0 && nums[mid] < nums[mid - 1]) {
                return nums[mid]
            }
            if (nums[mid] > nums[right]) {
                left = mid + 1
            } else {
                right = mid
            }
        }
        return nums[left]
    }

    fun findMin2(nums: IntArray): Int {
        var left = 0
        var right = nums.lastIndex

        while (left < right) {
            val mid = left + (right - left) / 2
            if (mid != 0 && nums[mid] < nums[mid - 1]) {
                return nums[mid]
            }
            if (mid != nums.lastIndex && nums[mid] > nums[mid + 1]) {
                return nums[mid + 1]
            }
            if (nums[mid] >= nums[left]) {
                if (nums[left] > nums[right]) {
                    left = mid + 1
                } else {
                    right = mid - 1
                }
            } else {
                if (nums[left] > nums[right]) {
                    right = mid - 1
                } else {
                    left = mid + 1
                }
            }
        }
        return nums[left]
    }

}