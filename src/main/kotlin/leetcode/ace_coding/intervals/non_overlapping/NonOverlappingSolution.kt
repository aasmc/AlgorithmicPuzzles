package leetcode.ace_coding.intervals.non_overlapping

class NonOverlappingSolution {

    fun eraseOverlapIntervals(intervals: Array<IntArray>): Int {
        intervals.sortWith(compareBy { it[0] })

        var cnt = 0
        var prevEnd = intervals[0][1]
        for (i in 1 until intervals.size) {
            val (start, end) = intervals[i]
            // check if intervals don't overlap
            if (start >= prevEnd) {
                // if intervals don't overlap, update the end value
                prevEnd = end
            } else { // intervals overlap
                ++cnt
                // select the end value as the minimum of the previous end and current end,
                // since we want to minimize the number of removed intervals. Selecting
                // the interval with the smaller end value ensures, that the interval
                // will have lesser chances to overlap with other intervals.
                prevEnd = minOf(prevEnd, end)
            }
        }
        return cnt
    }


}