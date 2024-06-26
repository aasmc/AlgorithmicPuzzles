package leetcode.easy.two_pointers.intersection_of_two_arrays

class IntersectionOfTwoArraysSolution {

    fun intersection(nums1: IntArray, nums2: IntArray): IntArray {
        val set = nums1.toHashSet()
        set.retainAll(nums2.toHashSet())
        return set.toIntArray()
    }

}