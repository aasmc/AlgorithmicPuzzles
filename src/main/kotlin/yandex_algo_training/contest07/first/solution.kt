package yandex_algo_training.contest07.first

import java.util.ArrayDeque
import java.util.ArrayList

fun main() {
    val vertices = readLine()!!.trim().toInt()

    val graph = Array<ArrayList<Int>>(vertices + 1) {
        ArrayList()
    }

    repeat(vertices) { v ->
        val neighbours = readLine()!!.trim().split(" ").map { it.toInt() }
        val current = graph[v + 1]
        for ((idx, n) in neighbours.withIndex()) {
            if (n != 0) current.add(idx + 1)
        }
    }
    val (from, to) = readLine()!!.trim().split(" ").map { it.toInt() }
    println(findShortestPath(graph, vertices, from, to))
}

fun findShortestPath(g: Array<ArrayList<Int>>, vertices: Int, from: Int, to: Int): Int {
    val visited = BooleanArray(vertices + 1)
    val distances = IntArray(vertices + 1) {
        Int.MAX_VALUE
    }
    distances[from] = 0
    fun bfs(source: Int) {
        visited[source] = true
        val queue = ArrayDeque<Int>()
        queue.addLast(source)
        while (queue.isNotEmpty()) {
            val current = queue.removeFirst()
            val adj = g[current]
            for (v in adj) {
                if (!visited[v]) {
                    if (distances[v] > distances[current] + 1) {
                        distances[v] = distances[current] + 1
                    }
                    visited[v] = true
                    queue.addLast(v)
                }
            }
        }
    }
    bfs(from)
    return if (distances[to] == Int.MAX_VALUE) -1 else distances[to]
}
