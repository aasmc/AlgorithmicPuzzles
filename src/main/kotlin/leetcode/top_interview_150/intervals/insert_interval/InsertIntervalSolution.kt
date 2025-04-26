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
                result.add(currentInterval)
            } else {
                newInterval[0] = minOf(newInterval.start(), currentInterval.start())
                newInterval[1] = maxOf(newInterval.end(), currentInterval.end())
            }
        }
        result.add(newInterval)
        return result.toTypedArray()
    }

    private fun IntArray.start() = this[0]
    private fun IntArray.end() = this[1]

    private fun newIntervalIsToRightOfCurrent(
        newInterval: IntArray,
        currentInterval: IntArray
    ) = newInterval.start() > currentInterval.end()

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
    ) = newInterval.end() < currentInterval.start()

}