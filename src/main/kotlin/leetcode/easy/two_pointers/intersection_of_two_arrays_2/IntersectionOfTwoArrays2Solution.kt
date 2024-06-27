package leetcode.easy.two_pointers.intersection_of_two_arrays_2

class IntersectionOfTwoArrays2Solution {

    fun intersect(nums1: IntArray, nums2: IntArray): IntArray {
        nums1.sort()
        nums2.sort()

        var nIdx = 0
        var mIdx = 0
        val result = arrayListOf<Int>()
        while (nIdx < nums1.size && mIdx < nums2.size) {
            while (nIdx < nums1.size && nums1[nIdx] < nums2[mIdx]) {
                ++nIdx
            }
            if (nIdx >= nums1.size) {
                break
            }
            while (mIdx < nums2.size && nums2[mIdx] < nums1[nIdx]) {
                ++mIdx
            }
            if (mIdx >= nums2.size) {
                break
            }
            if (nums1[nIdx] == nums2[mIdx]) {
                result.add(nums1[nIdx])
                ++nIdx
                ++mIdx
            }
        }
        return result.toIntArray()
    }


    fun intersect2(nums1: IntArray, nums2: IntArray): IntArray {
        val nMap = hashMapOf<Int, Pair<Int, Boolean>>()
        val mMap = hashMapOf<Int, Int>()
        nums1.forEach { num ->
            nMap.merge(num, 1 to false) { left, right ->
                left.first + right.first to false
            }
        }

        nums2.forEach { num ->
            mMap.merge(num, 1, Int::plus)
        }

        mMap.forEach { (key, value) ->
            if (nMap.containsKey(key)) {
                nMap[key] = minOf(nMap[key]!!.first, value) to true
            }
        }
        val result = arrayListOf<Int>()

        nMap.forEach { (key, value) ->
            if (value.second) {
                repeat(value.first) {
                    result.add(key)
                }
            }
        }
        return result.toIntArray()
    }

}