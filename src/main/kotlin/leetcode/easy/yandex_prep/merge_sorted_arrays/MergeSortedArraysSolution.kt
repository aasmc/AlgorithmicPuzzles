package leetcode.easy.yandex_prep.merge_sorted_arrays

class MergeSortedArraysSolution {

    fun merge(nums1: IntArray, m: Int, nums2: IntArray, n: Int): Unit {
        var lastIdx = m + n - 1
        var firstIdx = m - 1
        var secondIdx = n - 1
        while (secondIdx >= 0) {
            nums1[lastIdx--] = if (firstIdx >= 0 && nums1[firstIdx] >= nums2[secondIdx]) {
                nums1[firstIdx--]
            } else {
                nums2[secondIdx--]
            }
        }
    }

}