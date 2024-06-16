package leetcode.easy.arrays.merge_sorted_array

class MergeSortedArraySolution {
    fun merge(nums1: IntArray, m: Int, nums2: IntArray, n: Int): Unit {
        var lastIdx = m + n - 1
        var firstIdx = m - 1
        var secondIdx = n - 1
        while (secondIdx >= 0) {
            if (firstIdx >= 0 && nums1[firstIdx] >= nums2[secondIdx]) {
                nums1[lastIdx--] = nums1[firstIdx--]
            } else {
                nums1[lastIdx--] = nums2[secondIdx--]
            }
        }
    }
}