package leetcode.top_interview_150.intervals.arrows_balloons

class ArrowsBalloonsSolution {

    fun findMinArrowShots(points: Array<IntArray>): Int {
        // sort by start of the interval
        points.sortBy { it[0] }
        var cnt = 1
        var prevEnd = points[0].end()
        for (i in 1..points.lastIndex) {
            val currentInterval = points[i]
            if (currentInterval.start() > prevEnd) {
                ++cnt
                prevEnd = currentInterval.end()
            } else {
                prevEnd = minOf(prevEnd, currentInterval.end())
            }
        }
        return cnt
    }

    private fun IntArray.end(): Int {
        return this[1]
    }

    private fun IntArray.start(): Int {
        return this[0]
    }

}