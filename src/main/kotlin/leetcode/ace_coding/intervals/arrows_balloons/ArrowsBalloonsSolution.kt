package leetcode.ace_coding.intervals.arrows_balloons

class ArrowsBalloonsSolution {

    fun findMinArrowShots(points: Array<IntArray>): Int {
        points.sortWith(compareBy { it[0] })
        var cnt = 1
        var prevEnd = points[0][1]

        for (i in 1 until points.size) {
            val (start, end) = points[i]
            if (start > prevEnd) {
                ++cnt
                prevEnd = end
            } else {
                prevEnd = minOf(prevEnd, end)
            }
        }
        return cnt
    }


}