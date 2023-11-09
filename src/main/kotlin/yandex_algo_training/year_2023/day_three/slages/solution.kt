package yandex_algo_training.year_2023.day_three.slages

import java.util.PriorityQueue

fun main() {
    val n = readln().trim().toInt()
    val descriptions = Array<CityDescription?>(n + 1) { null }
    repeat(n) { city ->
        val (waitTime, speed) = readln().trim().split("\\s+".toRegex()).map { it.toInt() }
        descriptions[city + 1] = CityDescription(city + 1, waitTime, speed)
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

private fun solve(descriptions: Array<CityDescription?>, roads: Array<MutableList<Road>>, n: Int): ShortestPath {
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
    descriptions: Array<CityDescription?>,
    roads: Array<MutableList<Road>>,
    n: Int
): ShortestPath {
    val prev = linkedSetOf<Int>()
    val visited = IntArray(n + 1)
    val unhandled = PriorityQueue<CityTime>(Comparator.comparingDouble { it.curTime })
    val times = DoubleArray(n + 1) { Double.POSITIVE_INFINITY }
    times[start] = 0.0
    unhandled.offer(CityTime(start, descriptions[start]!!.waitTime.toDouble()))
    while (unhandled.isNotEmpty()) {
        val current = unhandled.poll()!!
        if (current.city == 1) {
            prev.add(current.city)
            break
        }
        if (visited[current.city] < 2) {
            visited[current.city]++
            val adjacent = roads[current.city]
            for (road in adjacent) {
                if (visited[road.to] < 2) {
                    val (cityFrom, cityTo, distance) = road
                    val prevSpeed = current.prevSpeed
                    val prevTime = times[current.city]
                    val (currentNum, waitTime, currentSpeed) = descriptions[cityFrom]!!
                    val noChangeTravelTime = distance * 1.0 / prevSpeed
                    val withChangeTravelTime = distance * 1.0 / currentSpeed + waitTime
                    var speed = currentSpeed.toDouble()
                    val time = if (noChangeTravelTime <= withChangeTravelTime) {
                        speed = prevSpeed
                        noChangeTravelTime
                    } else {
                        withChangeTravelTime
                    }
                    if (times[cityTo] > time + times[start] || times[cityTo] == 0.0 && visited[cityTo] >= 1) {
                        times[cityTo] = time + times[current.city]
                        unhandled.offer(CityTime(cityTo, times[cityTo], speed))
                        prev.add(cityFrom)
                    }
                }
            }
        }
    }
    return ShortestPath(times[1], prev.toList())
}

data class CityTime(
    val city: Int,
    val curTime: Double,
    val prevSpeed: Double = 0.0000000000000000001,
)

data class CityDescription(
    val number: Int,
    val waitTime: Int,
    val speed: Int
)

data class Road(
    val from: Int,
    val to: Int,
    val distance: Int
)