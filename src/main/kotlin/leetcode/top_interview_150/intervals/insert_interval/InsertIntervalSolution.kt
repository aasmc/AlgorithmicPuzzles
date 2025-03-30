package leetcode.top_interview_150.intervals.insert_interval

class InsertIntervalSolution {

    fun insert(intervals: Array<IntArray>, newInterval: IntArray): Array<IntArray> {
        if (intervals.isEmpty()) {
            return arrayOf(newInterval)
        }
        val result = mutableListOf<IntArray>()
        for (i in intervals.indices) {
            if (newInterval[1] < intervals[i][0]) {
                result.add(newInterval)
                for (j in i..intervals.lastIndex) {
                    result.add(intervals[j])
                }
                return result.toTypedArray()
            } else if (newInterval[0] > intervals[i][1]) {
                result.add(intervals[i])
            } else {
                newInterval[0] = minOf(newInterval[0], intervals[i][0])
                newInterval[1] = maxOf(newInterval[1], intervals[i][1])
            }
        }
        result.add(newInterval)
        return result.toTypedArray()
    }

}