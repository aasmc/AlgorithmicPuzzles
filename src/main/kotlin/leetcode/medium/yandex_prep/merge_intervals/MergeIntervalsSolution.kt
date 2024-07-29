package leetcode.medium.yandex_prep.merge_intervals

class MergeIntervalsSolution {

    fun merge(intervals: Array<IntArray>): Array<IntArray> {
        intervals.sortBy { it[0] }
        val result = mutableListOf<IntArray>()
        var prevEnd = intervals[0][1]
        var prevStart = intervals[0][0]
        for (i in 1 until intervals.size) {
            val (curStart, curEnd) = intervals[i]
            if (curStart <= prevEnd) {
                prevEnd = maxOf(prevEnd, curEnd)
            } else {
                result.add(intArrayOf(prevStart, prevEnd))
                prevStart = curStart
                prevEnd = curEnd
            }
        }
        result.add(intArrayOf(prevStart, prevEnd))
        return result.toTypedArray()
    }

}