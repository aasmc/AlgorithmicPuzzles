package leetcode.easy.yandex_prep.intersection_of_arrays

class IntersectionOfArraysSolution {

    fun intersect(nums1: IntArray, nums2: IntArray): IntArray {
        nums1.sort()
        nums2.sort()

        var firstIdx = 0
        var secondIdx = 0
        val result = arrayListOf<Int>()
        while (firstIdx < nums1.size && secondIdx < nums2.size) {
            if (nums1[firstIdx] == nums2[secondIdx]) {
                result.add(nums1[firstIdx])
                firstIdx++
                secondIdx++
            }
            if (secondIdx == nums2.size || firstIdx == nums1.size) {
                break
            }
            while (firstIdx < nums1.size && nums1[firstIdx] < nums2[secondIdx]) {
                ++firstIdx
            }
            if (firstIdx == nums1.size) {
                break
            }
            while (secondIdx < nums2.size && nums2[secondIdx] < nums1[firstIdx]) {
                ++secondIdx
            }
        }
        return result.toIntArray()
    }

}