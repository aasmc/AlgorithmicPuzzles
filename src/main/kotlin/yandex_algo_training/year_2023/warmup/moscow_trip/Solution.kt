package yandex_algo_training.year_2023.warmup.moscow_trip

import kotlin.math.sqrt


fun main() {
    val (xa, ya, xb, yb) = readln().split(" ").map { it.toInt() }
    solve(xa, ya, xb, yb)
}

private fun solve(xa: Int, ya: Int, xb: Int, yb: Int) {
    // case 1: 0 A B - on the same line
    if (onSameLine(xa, ya, xb, yb, 0, 0)) {
        println(distance(xa, ya, xb, yb))
    } else {
        val distanceToStart = distance(xa, ya ,0, 0)
        val distanceFromStartToB = distance(0, 0, xb, yb)
    }
}

private fun onSameLine(xa: Int, ya: Int, xb: Int, yb: Int, xz: Int, yz: Int): Boolean {
    val dx = xa - xb
    val dy = ya - yb
    val dx1 = xa - xz
    val dy1 = ya - yz
    return dx1 * dy == dy1 * dx
}

private fun distance(xa: Int, ya: Int, xb: Int, yb: Int): Double {
    val xDiff = xb - xa
    val yDiff = yb - ya
    return sqrt(xDiff * xDiff * 1.0 + yDiff * yDiff)
}