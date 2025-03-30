package leetcode.medium.intervals.merge_intervals

class MergeIntervalsSolution {

    fun merge(intervals: Array<IntArray>): Array<IntArray> {
        // sort intervals by start time
        intervals.sortBy { it[0] }
        // list of resulting intervals
        val res = arrayListOf<IntArray>()
        res.add(intervals[0])
        for (i in 1 until intervals.size) {
            val prev = res.last()
            val current = intervals[i]
            // case 1: current interval overlaps with the previous one (start of the current
            // interval is less than the end of the previous interval)
            // and current interval's end is greater than the end of the previous interval.
            // This means we need to merge the two intervals. We merge them by updating
            // the end of the previous interval, setting it to the end of the current
            // interval
            if (current[0] <= prev[1] && current[1] > prev[1]) {
                prev[1] = current[1]
            } else if (current[0] > prev[1]) { // case 2: current interval is after previous interval
                res.add(current)
            }
        }
        return res.toTypedArray()
    }
}