package leetcode.medium.intervals.non_overlapping_intervals

class NonOverlappingIntervalsSolution {

    fun eraseOverlapIntervals(intervals: Array<IntArray>): Int {
        intervals.sortBy { it[0] }

        var prevEnd = intervals[0][1]
        var count = 0
        for (i in 1 until intervals.size) {
            val (start, end) = intervals[i]
            if (start >= prevEnd) {
                prevEnd = end
            } else {
                ++count
                prevEnd = minOf(prevEnd, end)
            }
        }
        return count
    }

}