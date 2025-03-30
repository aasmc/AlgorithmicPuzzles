package leetcode.top_interview_150.array_string.merge_sorted_arrays

class MergeSortedSolution {

    fun merge(nums1: IntArray, m: Int, nums2: IntArray, n: Int): Unit {
        var left  = m - 1
        var right = n - 1
        var idx = n + m - 1
        // add elements to the end of the array
        while (right >= 0) {
            if (left >= 0 && nums1[left] >= nums2[right]) {
                nums1[idx--] = nums1[left--]
            } else {
                nums1[idx--] = nums2[right--]
            }
        }
    }

    fun mergeAdditionalMemory(nums1: IntArray, m: Int, nums2: IntArray, n: Int): Unit {
        var left = 0
        var right = 0
        val interim = IntArray(m)
        System.arraycopy(nums1, 0, interim, 0, m)
        var idx = 0

        while (left < m && right < n) {
            if (interim[left] <= nums2[right]) {
                nums1[idx++] = interim[left++]
            } else {
                nums1[idx++] = nums2[right++]
            }
        }

        while (left < m) {
            nums1[idx++] = interim[left++]
        }

        while (right < n) {
            nums1[idx++] = nums2[right++]
        }
    }




}