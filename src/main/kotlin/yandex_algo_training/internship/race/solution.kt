package yandex_algo_training.internship.race

fun main() {
    val (n, t, s) = readln().trim().split("\\s+".toRegex()).map { it.toInt() }
    val speeds = readln().trim().split("\\s+".toRegex()).map { it.toLong() }

    val distOne = speeds[0] * t
    val lapsOne = speeds[0] * t / s
    val lastLapDist = distOne % s
    var cnt = 0L
    for (i in 1 until speeds.size) {
        if (speeds[i] < speeds[0]) {
            val dist = speeds[i] * t
            val laps = dist / s
            val currentLastLapDist = dist % s
            val takeOvers = if (laps == 0L) lapsOne else lapsOne - laps - 1
            cnt += takeOvers
            if (lastLapDist > currentLastLapDist) {
                ++cnt
            }
        }
    }
    println(cnt)
}