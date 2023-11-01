package yandex_algo_training.year_2023.warmup.moscow_trip

import kotlin.math.*


fun main() {
    val (xa, ya, xb, yb) = readln().split(" ").map { it.toInt() }
    solve(xa, ya, xb, yb)
}

private fun solve(xa: Int, ya: Int, xb: Int, yb: Int) {
    // case 1: 0 A B - on the same line
    if (onSameLine(xa, ya, xb, yb, 0, 0)) {
        println(distance(xa, ya, xb, yb))
    } else {
        // Case 2: not on the same line
        // need to find min distance between:
        // - moving along the circumference with radius A towards the B point
        //  then towards the B point along the line from 0 to B
        // - moving towards 0, then moving towards B along the line from 0 to B
        // - moving towards 0 until circumference with radius B, then moving along the
        // circumference towards B
        val radiusFromStartToA = distance(xa, ya, 0, 0)
        val distanceFromStartToB = distance(0, 0, xb, yb)
        val pathOne = radiusFromStartToA + distanceFromStartToB

        val distanceFromCircumferenceToB = abs(radiusFromStartToA - distanceFromStartToB)
        val atanA = Math.atan2(ya.toDouble(), xa.toDouble())
        val atanB = Math.atan2(yb.toDouble(), xb.toDouble())
        var radians = atanB - atanA
        // -PI..PI
        while (radians > Math.PI) {
            radians -= (2 * Math.PI)
        }
        while (radians < -Math.PI) {
            radians += (2 * Math.PI)
        }

        val circumferenceASectorLength = abs(radiusFromStartToA * radians)
        val pathTwo = distanceFromCircumferenceToB + circumferenceASectorLength

        val circumferenceBSectorLength = abs(distanceFromStartToB * radians)
        val pathThree = distanceFromCircumferenceToB + circumferenceBSectorLength

        val result = minOf(pathOne, pathTwo, pathThree)
        println(result)
    }
}

private fun onSameLine(xa: Int, ya: Int, xb: Int, yb: Int, xz: Int, yz: Int): Boolean {
    val dx = (xa - xb).toDouble()
    val dy = (ya - yb).toDouble()
    val dx1 = (xa - xz).toDouble()
    val dy1 = (ya - yz).toDouble()
    return dx1 * dy == dy1 * dx
}

private fun distance(xa: Int, ya: Int, xb: Int, yb: Int): Double {
    val xDiff = (xb - xa).toDouble()
    val yDiff = (yb - ya).toDouble()
    return sqrt(xDiff * xDiff + yDiff * yDiff)
}