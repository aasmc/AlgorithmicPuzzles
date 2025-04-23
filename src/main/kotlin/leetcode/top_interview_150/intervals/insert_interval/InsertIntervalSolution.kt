package leetcode.top_interview_150.intervals.insert_interval

class InsertIntervalSolution {

    fun insert(intervals: Array<IntArray>, newInterval: IntArray): Array<IntArray> {
        if (intervals.isEmpty()) {
            return arrayOf(newInterval)
        }
        val result = mutableListOf<IntArray>()
        for (i in intervals.indices) {
            val currentInterval = intervals[i]
            if (newIntervalIsToTheLeftOfCurrent(newInterval, currentInterval)) {
                result.add(newInterval)
                addRemainingIntervals(i, intervals, result)
                return result.toTypedArray()
            } else if (newIntervalIsToRightOfCurrent(newInterval, currentInterval)) {
                result.add(intervals[i])
            } else {
                newInterval[0] = minOf(newInterval[0], currentInterval[0])
                newInterval[1] = maxOf(newInterval[1], currentInterval[1])
            }
        }
        result.add(newInterval)
        return result.toTypedArray()
    }

    private fun newIntervalIsToRightOfCurrent(
        newInterval: IntArray,
        currentInterval: IntArray
    ) = newInterval[0] > currentInterval[1]

    private fun addRemainingIntervals(
        i: Int,
        intervals: Array<IntArray>,
        result: MutableList<IntArray>
    ) {
        for (j in i..intervals.lastIndex) {
            result.add(intervals[j])
        }
    }

    private fun newIntervalIsToTheLeftOfCurrent(
        newInterval: IntArray,
        currentInterval: IntArray
    ) = newInterval[1] < currentInterval[0]

}