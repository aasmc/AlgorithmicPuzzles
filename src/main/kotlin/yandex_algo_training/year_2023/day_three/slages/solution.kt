package yandex_algo_training.year_2023.day_three.slages

import java.util.PriorityQueue

fun main() {
    val n = readln().trim().toInt()
    val descriptions = Array<City?>(n + 1) { null }
    repeat(n) { city ->
        val (waitTime, speed) = readln().trim().split("\\s+".toRegex()).map { it.toInt() }
        descriptions[city + 1] = City(city + 1, waitTime, speed)
    }
    val roads = Array<MutableList<Road>>(n + 1) {
        mutableListOf()
    }
    repeat(n - 1) {
        val (from, to, dist) = readln().trim().split("\\s+".toRegex()).map { it.toInt() }
        roads[from].add(Road(from, to, dist))
        roads[to].add(Road(to, from, dist))
    }

    val res = solve(descriptions, roads, n)
    println(res.time)
    println(res.path.joinToString(separator = " "))
}

private fun solve(descriptions: Array<City?>, roads: Array<MutableList<Road>>, n: Int): ShortestPath {
    var max = findShortestTimeToCapitalFrom(n, descriptions, roads, n)
    for (i in n - 1 downTo 2) {
        val cur = findShortestTimeToCapitalFrom(i, descriptions, roads, n)
        if (cur.time >= max.time) {
            max = cur
        }
    }
    return max
}

data class ShortestPath(
    val time: Double,
    val path: List<Int>
)

private fun findShortestTimeToCapitalFrom(
    start: Int,
    cities: Array<City?>,
    roads: Array<MutableList<Road>>,
    n: Int
): ShortestPath {
    val unhandled = PriorityQueue<CityTime>(Comparator.comparingDouble { it.curTime })
    val times = DoubleArray(n + 1) { Double.POSITIVE_INFINITY }
    times[start] = 0.0
    unhandled.offer(CityTime(start, 0.0))
    val path = mutableListOf<Int>()
    while (unhandled.isNotEmpty()) {
        val current = unhandled.poll()!!
        if (current.city == 1) {
            path.clear()
            times[1] = current.curTime
            var c: CityTime? = current
            while (c != null) {
                path.add(c.city)
                c = c.prev
            }
        }
        if (times[current.city] < current.curTime) continue
        val adjacent = roads[current.city]
        for (road in adjacent) {
            val (cityFrom, cityTo, distance) = road
            val prevSpeed = current.prevSpeed
            val (currentNum, waitTime, currentSpeed) = cities[cityFrom]!!
            val noChangeTravelTime = distance * 1.0 / prevSpeed
            val withChangeTravelTime = distance * 1.0 / currentSpeed + waitTime
            var speed = currentSpeed.toDouble()
            val time = if (noChangeTravelTime <= withChangeTravelTime) {
                speed = prevSpeed
                noChangeTravelTime
            } else {
                withChangeTravelTime
            }
            if (times[cityTo] > time + times[start]) {
                times[cityTo] = time + times[current.city]
                unhandled.offer(CityTime(cityTo, times[cityTo], speed, current))
            }
        }
    }

    return ShortestPath(times[1], path.reversed())
}

data class CityTime(
    val city: Int,
    val curTime: Double,
    val prevSpeed: Double = 0.0000000000000000001,
    val prev: CityTime? = null
)

data class City(
    val number: Int,
    val waitTime: Int,
    val speed: Int
)

data class Road(
    val from: Int,
    val to: Int,
    val distance: Int
)