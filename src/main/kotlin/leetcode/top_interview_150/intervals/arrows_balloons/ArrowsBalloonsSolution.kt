package leetcode.top_interview_150.intervals.arrows_balloons

class ArrowsBalloonsSolution {

    fun findMinArrowShots(points: Array<IntArray>): Int {
        // sort by start of the interval
        points.sortBy { it[0] }
        var cnt = 1
        var prevEnd = points[0][1]
        for (i in 1..points.lastIndex) {
            if (points[i][0] > prevEnd) {
                ++cnt
                prevEnd = points[i][1]
            } else {
                prevEnd = minOf(prevEnd, points[i][1])
            }
        }
        return cnt
    }

}