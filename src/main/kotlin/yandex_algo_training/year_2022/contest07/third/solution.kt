package yandex_algo_training.year_2022.contest07.third

import java.util.ArrayList


fun main() {
    val (n, m, s, t, q) = readLine()!!.trim().split(" ")
        .map { it.toInt() }
    val coords = ArrayList<Pair<Int, Int>>(q)
    repeat(q) {
        val (row, col) = readLine()!!.trim().split(" ").map { it.toInt() }
        coords.add(row to col)
    }

    println(findMinJumps(s, t, n, m, coords))
}

fun findMinJumps(
    s: Int,
    t: Int,
    n: Int,
    m: Int,
    coords: List<Pair<Int, Int>>
): Int {

    val visited = Array<BooleanArray>(n + 1) {
        BooleanArray(m + 1)
    }

    val distances = Array<IntArray>(n + 1) {
        IntArray(m + 1) { Int.MAX_VALUE }
    }

    distances[s][t] = 0

    fun bfs(row: Int, col: Int) {
        visited[row][col] = true
        val queue = ArrayDeque<Pair<Int, Int>>()
        queue.addLast(row to col)
        while (queue.isNotEmpty()) {
            val current = queue.removeFirst()
            val adj = getAdj(n, m, current.first, current.second)
            for (p in adj) {
                if (!visited[p.first][p.second]) {
                    if (distances[p.first][p.second] > distances[current.first][current.second] + 1) {
                        distances[p.first][p.second] = distances[current.first][current.second] + 1
                    }
                    queue.addLast(p)
                    visited[p.first][p.second] = true
                }
            }
        }
    }

    var result = 0
    bfs(s, t)
    for (coord in coords) {
        if (distances[coord.first][coord.second] == Int.MAX_VALUE) {
            return -1
        } else {
            result += distances[coord.first][coord.second]
        }
    }
    return result
}

private fun getAdj(n: Int, m: Int, row: Int, col: Int): List<Pair<Int, Int>> {
    val rows = intArrayOf(-1, -2, 1, 2, -1, -2, 1, 2)
    val cols = intArrayOf(-2, -1, -2, -1, 2, 1, 2, 1)
    val res = mutableListOf<Pair<Int, Int>>()
    for (i in 0 until 8) {
        val r = rows[i]
        val c = cols[i]
        if (r + row in 1 .. n &&
            c + col in 1 .. m
        ) {
            res.add(r + row to c + col)
        }
    }
    return res
}
