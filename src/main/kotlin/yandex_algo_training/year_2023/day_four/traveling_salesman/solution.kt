package yandex_algo_training.year_2023.day_four.traveling_salesman


fun main() {
    val n = readln().toInt()
    val graph = Array<IntArray>(n) {
        IntArray(n)
    }

    repeat(n) {
        graph[it] = readln().trim().split(" ").map { it.toInt() }.toIntArray()
    }
    val res: Int = solve(graph, n)
    println(if (res < 0) -1 else res)
}

fun solve(graph: Array<IntArray>, n: Int): Int {
    if (n == 2) {
        return graph[0][1] + graph[1][0]
    }
    val allVisited = (1 shl n) - 1 // all ones, e.g. n = 4 => allVisited = 0x1111
    fun helper(mask: Int, pos: Int): Int {
        if (mask == allVisited) {
            return graph[pos][0]
        }
        var ans = Int.MAX_VALUE
        // try to visit unvisited cities
        for (city in 0 until n) {
            // if not visited
            val cityMask = 1 shl city
            if ((mask and cityMask) == 0 && graph[pos][city] != 0) { // not visited
                val newAns = graph[pos][city] + helper(mask or (1 shl city), city)
                ans = minOf(ans, newAns)
            }
        }
        return ans
    }
    return helper(1, 0)
}


