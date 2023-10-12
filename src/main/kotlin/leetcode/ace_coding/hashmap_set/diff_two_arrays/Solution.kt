package leetcode.ace_coding.hashmap_set.diff_two_arrays

fun findDifference(nums1: IntArray, nums2: IntArray): List<List<Int>> {
    val unique1 = nums1.toHashSet()
    unique1.removeAll(nums2.toSet())
    val unique2 = nums2.toHashSet()
    unique2.removeAll(nums1.toSet())
    return listOf(unique1.toList(), unique2.toList())
}