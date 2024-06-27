package leetcode.easy.two_pointers.intersection_of_two_arrays_2

class IntersectionOfTwoArrays2Solution {

    fun intersect(nums1: IntArray, nums2: IntArray): IntArray {
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