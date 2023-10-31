package yandex_algo_training.year_2022.contest06.third

import java.util.ArrayList

fun main() {
    val (n, m) = readLine()!!.trim().split(" ").map { it.toInt() }
    val g = Array<ArrayList<Int>>(n + 1) {
        ArrayList()
    }
    repeat(m) {
        val (from, to)  = readLine()!!.trim().split(" ").map { it.toInt() }
        g[from].add(to)
        g[to].add(from)
    }
    if (isBipartite(g, n)) {
        println("YES")
    } else {
        println("NO")
    }
}

fun isBipartite(g: Array<ArrayList<Int>>, vertexCount: Int): Boolean {
    val colors = arrayOfNulls<Boolean>(vertexCount + 1)
    fun dfs(v: Int, color: Boolean): Boolean {
        // our neighbour tried to color us with the same color
        // graph is not bipartite
        colors[v]?.let {
            return@dfs it == color
        }
        // color this vertex
        colors[v] = color
        for (u in g[v]) {
            // try to color all our adjacent vertices with different color
            // if failed - graph is not bipartite
            if (!dfs(u, !color)) return false
        }
        // if success -> graph is bipartite (connected component of a graph)
        return true
    }
    for (i in 1..vertexCount) {
        if (colors[i] == null) {
            if (!dfs(i, true)) return false
        }
    }
    return true
}