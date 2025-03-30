package leetcode.medium.intervals.min_balloons

class MinBalloonsSolution {

    fun findMinArrowShots(points: Array<IntArray>): Int {
        points.sortBy { it[0] }
        var prevEnd = points.first()[1]
        var count = 1
        for (i in 1 until points.size) {
            val current = points[i]
            // case 1: current interval overlaps with previous interval
            // in this case we update the prevEnd to the minimum of the ends of the intervals,
            // this ensures that when we compare the prevEnd with the next interval, we
            // can correctly find out whether we can use one arrow to burst previous, current
            // and the next balloons
            if (current[0] <= prevEnd) {
                prevEnd = minOf(prevEnd, current[1])
            } else { // case 2: current interval doesn't overlap with the previous interval
                ++count
                prevEnd = current[1]
            }
        }
        return count
    }

}