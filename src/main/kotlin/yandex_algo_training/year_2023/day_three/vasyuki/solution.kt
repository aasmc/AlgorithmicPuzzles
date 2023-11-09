package yandex_algo_training.year_2023.day_three.vasyuki

import java.util.PriorityQueue

fun main() {
    val n = readln().trim().toInt()
    val (d, v) = readln().trim().split(" ").map { it.toInt() }
    val r = readln().trim().toInt()
    val graph = Array<MutableList<Bus>>(n + 1) {
        mutableListOf()
    }
    repeat(r) {
        val (from, timeFrom, to, timeTo) = readln().trim().split(" ").map { it.toInt() }
        graph[from].add(Bus(from, timeFrom, to, timeTo))
    }
    println(findShortestTime(graph, d, v, n))
}

private fun findShortestTime(graph: Array<MutableList<Bus>>, from: Int, to: Int, n: Int): Int {
    val visited = BooleanArray(n + 1)
    val times = IntArray(n + 1) { Int.MAX_VALUE }
    times[from] = 0
    val unhandled = PriorityQueue<BusRace>(Comparator.comparingInt { it.time })
    unhandled.add(BusRace(from, 0))
    while (unhandled.isNotEmpty()) {
        val current = unhandled.poll()!!
        if (!visited[current.num]) {
            val adjacent = graph[current.num]
            visited[current.num] = true
            for(bus in adjacent) {
                val (cityFrom, startTime, cityTo, arrivalTime) = bus
                if (!visited[cityTo]) {
                    if (startTime >= times[cityFrom]) {
                        times[cityTo] = minOf(times[cityTo], arrivalTime)
                        unhandled.offer(BusRace(cityTo, times[cityTo]))
                    }
                }
            }
        }

    }
    return if (times[to] == Int.MAX_VALUE) -1 else times[to]
}

data class BusRace(
    val num: Int,
    val time: Int
)

data class Bus(
    val cityFrom: Int,
    val startTime: Int,
    val cityTo: Int,
    val arrivalTime: Int
)