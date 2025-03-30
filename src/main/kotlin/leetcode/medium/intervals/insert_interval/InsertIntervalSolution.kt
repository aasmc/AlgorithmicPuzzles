package leetcode.medium.intervals.insert_interval

class InsertIntervalSolution {

    fun insert(intervals: Array<IntArray>, newInterval: IntArray): Array<IntArray> {
        if (intervals.isEmpty()) {
            return arrayOf(newInterval)
        }
        val res = arrayListOf<IntArray>()
        for (i in intervals.indices) {
            val current = intervals[i]
            if (isCurrentIntervalAfter(current, newInterval)) {
                res.add(newInterval)
                res.add(current)
                for (j in i + 1 until intervals.size) {
                    res.add(intervals[j])
                }
                return res.toTypedArray()
            } else if (isCurrentIntervalBefore(current, newInterval)) {
                res.add(current)
            } else {
                newInterval[0] = minOf(newInterval[0], current[0])
                newInterval[1] = maxOf(newInterval[1], current[1])
            }
        }
        res.add(newInterval)
        return res.toTypedArray()
    }

    private fun isCurrentIntervalBefore(current: IntArray, other: IntArray): Boolean {
        return current[1] < other[0]
    }

    private fun isCurrentIntervalAfter(current: IntArray, other: IntArray): Boolean {
        return other[1] < current[0]
    }

}