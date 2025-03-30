package leetcode.medium.yandex_prep.interval_list_intersections

class IntervalListIntersections {

    fun intervalIntersection(firstList: Array<IntArray>, secondList: Array<IntArray>): Array<IntArray> {
        val result = mutableListOf<IntArray>()
        var fIdx = 0
        var sIdx = 0
        while (fIdx < firstList.size && sIdx < secondList.size) {
            val left = firstList[fIdx]
            val right = secondList[sIdx]
            getIntersection(left, right)?.let { result.add(it) }
            if (left[1] < right[1]) {
                ++fIdx
            } else if (right[1] < left[1]) {
                ++sIdx
            } else {
                ++sIdx
                ++fIdx
            }
        }
        return result.toTypedArray()
    }

    private fun getIntersection(l: IntArray, r: IntArray): IntArray? {
        if (l[1] < r[0]) return null
        if (r[1] < l[0]) return null
        return intArrayOf(
            maxOf(l[0], r[0]),
            minOf(l[1], r[1])
        )
    }

}