package leetcode.top_interview_150.intervals.merge_intervals

class MergeIntervalsSolution {

    fun merge(intervals: Array<IntArray>): Array<IntArray> {
        intervals.sortBy { it[0] }
        val result = mutableListOf<IntArray>()
        result.add(intervals[0])
        for (i in 1..intervals.lastIndex) {
            val current = intervals[i]
            val prev = result.last()
            if (current[0] <= prev[1] && current[1] > prev[1]) {
                prev[1] = current[1]
            } else if (current[0] > prev[1]) {
                result.add(current)
            }
        }
        return result.toTypedArray()
    }

}