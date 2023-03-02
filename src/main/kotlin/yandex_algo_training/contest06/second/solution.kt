package yandex_algo_training.contest06.second

import java.util.ArrayList

fun main() {
    val (vertices, edges) = readLine()!!.split(" ").map { it.toInt() }
    val g = Array<ArrayList<Int>>(vertices + 1) {
        ArrayList()
    }
    repeat(edges) {
        val (from, to) = readLine()!!.split(" ").map { it.toInt() }
        g[from].add(to)
        g[to].add(from)
    }
    val scc = dfs(g, vertices)
    if (scc.isNotEmpty()) {
        println(scc.size)
        for (i in scc.indices) {
            val component = scc[i]
            println(component.size)
            val res = component.joinToString(separator = " ")
            println(res)
        }

    } else {
        for (i in 1..vertices) {
            println(1)
            println(i)
        }
    }
}


fun dfs(g: Array<ArrayList<Int>>, vertexCount: Int): ArrayList<ArrayList<Int>> {
    val visited = BooleanArray(vertexCount + 1)
    fun helper(source: Int, res: ArrayList<Int>) {
        visited[source] = true
        res.add(source)
        for (v in g[source]) {
            if (!visited[v]) {
                helper(v, res)
            }
        }
    }
    val total = ArrayList<ArrayList<Int>>(vertexCount)
    for (i in 1..vertexCount) {
        val res = arrayListOf<Int>()
        if (!visited[i]) {
            helper(i, res)
        }
        if (res.isNotEmpty()) total.add(res)
    }
    return total
}



