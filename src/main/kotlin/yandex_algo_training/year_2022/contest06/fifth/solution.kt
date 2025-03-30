package yandex_algo_training.year_2022.contest06.fifth

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
    val cycle = detectCycle(graph, vertices)
    if (cycle.isEmpty() || cycle.size == 2) {
        println("NO")
    } else {
        println("YES")
        val result = mutableListOf<Int>()
        val last = cycle.last()
        for (i in cycle.size - 1 downTo 0) {
            if (i != cycle.lastIndex && cycle[i] == last) break
            result.add(cycle[i])
        }
        println(result.size)
        println(result.reversed().joinToString(" "))

    }
}

fun detectCycle(g: Array<ArrayList<Int>>, vertexCount: Int): ArrayList<Int> {
    val visited = BooleanArray(vertexCount + 1)
    val result = arrayListOf<Int>()

    fun dfs(s: Int, p: Int): Boolean {
        visited[s] = true
        result.add(s)
        for (u in g[s]) {
            if (!visited[u]) {
                if (dfs(u, s)) {
                    return true
                }
            } else if (u != p) {
                result.add(u)
                return true
            }
        }
        result.removeLast()
        return false
    }

    for (i in 1..vertexCount) {
        if (!visited[i]) {
            if (dfs(i, -1)) {
                return result
            } else {
                result.clear()
            }
        }
    }
    return result
}