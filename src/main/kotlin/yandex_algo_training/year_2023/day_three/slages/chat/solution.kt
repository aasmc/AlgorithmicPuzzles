package yandex_algo_training.year_2023.day_three.slages.chat

import java.util.*


fun main() {
    val n = readln().trim().toInt()
    val cities = Array<City?>(n + 1) { null }
    repeat(n) { city ->
        val (waitTime, speed) = readln().trim().split("\\s+".toRegex()).map { it.toInt() }
        cities[city + 1] = City(waitTime, speed)
    }
    val roads = mutableListOf<MutableList<Road>>()
    for (i in 0..n) {
        roads.add(mutableListOf())
    }
    repeat(n - 1) {
        val (from, to, dist) = readln().trim().split("\\s+".toRegex()).map { it.toInt() }
        roads[from].add(Road(to, dist))
        roads[to].add(Road(from, dist))
    }
    findMaxTimeToCapital(n, roads, cities)
}

data class City(
    val timeToPrepare: Int,
    val speed: Int
)

data class Road(
    val to: Int,
    val distance: Int
)

class State(
    val city: Int,
    val time: Double,
    val speed: Int,
    var prev: State? = null
) : Comparable<State> {
    override fun compareTo(other: State): Int {
        return time.compareTo(other.time)
    }
}

fun findMaxTimeToCapital(
    n: Int,
    roads: List<List<Road>>,
    cities: Array<City?>
): Double {
    var maxTime = 0.0
    var longestPath = mutableListOf<Int>()
    val prevCity = hashMapOf<Int, Int>()
    for (start in 2..n) {
        val queue = PriorityQueue<State>()
        val times = DoubleArray(n + 1) { Double.MAX_VALUE }
        times[start] = 0.0
        queue.add(State(start, 0.0, cities[start]!!.speed))

        while (queue.isNotEmpty()) {
            val current = queue.poll()
            if (current.time > maxTime) {
                maxTime = current.time
                longestPath.clear()
                var path = current
                while (path != null) {
                    longestPath.add(path.city)
                    path = path.prev
                }
                break
            }
            if (times[current.city] < current.time) continue
            for (road in roads[current.city]) {
                val timeWithCurrent = current.time + road.distance.toDouble() / current.speed
                val timeWithNew =
                    current.time + cities[road.to]!!.timeToPrepare + road.distance.toDouble() / cities[road.to]!!.speed

                if (timeWithCurrent < times[road.to]) {
                    times[road.to] = timeWithCurrent
                    queue.add(State(road.to, timeWithCurrent, current.speed, current))
                }

                if (timeWithNew < times[road.to]) {
                    times[road.to] = timeWithNew
                    queue.add(State(road.to, timeWithNew, cities[road.to]!!.speed, current))
                }
            }
        }
    }

    val path = longestPath.reversed()
    println(maxTime)
    println(path.joinToString(separator = " "))
    return maxTime
}