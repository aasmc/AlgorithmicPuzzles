package yandex_algo_training.year_2022.contest06.fourth

import java.util.ArrayDeque
import java.util.ArrayList

fun main() {
    val (vertices, edges) = readLine()!!.split(" ").map { it.toInt() }
    val g = Array<ArrayList<Int>>(vertices + 1) {
        ArrayList()
    }
    repeat(edges) {
        val (from, to) = readLine()!!.split(" ").map { it.toInt() }
        g[from].add(to)
    }
    val res = topoSort(g, vertices)
    if (res.isEmpty()) {
        println(-1)
    } else {
        println(res.joinToString(" "))
    }

}

fun topoSort(g: Array<ArrayList<Int>>, vertexCount: Int): List<Int> {
    if (detectCycleInDirectedGraph(g, vertexCount)) return emptyList()

    val stack = ArrayDeque<Int>()
    val visited = BooleanArray(vertexCount + 1)

    fun dfs(source: Int) {
        visited[source] = true
        for (v in g[source]) {
            if (!visited[v]) {
                dfs(v)
            }
        }
        // place the farthest vertex reachable from initial source on the stack
        stack.push(source)
    }

    for (i in 1..vertexCount) {
        if (!visited[i]) {
            dfs(i)
        }
    }
    val result = ArrayList<Int>(vertexCount)
    while (stack.isNotEmpty()) {
        result.add(stack.pop())
    }
    return result
}

fun detectCycleInDirectedGraph(g: Array<ArrayList<Int>>, vertexCount: Int): Boolean {
    val visited = BooleanArray(vertexCount + 1)
    // tracks whether current vertex takes part in recursive call
    val recursionStack = BooleanArray(vertexCount + 1)
    fun dfs(source: Int): Boolean {
        visited[source] = true
        recursionStack[source] = true
        for (v in g[source]) {
            if (!visited[v] && dfs(v)) {
                return true
                // if this vertex was visited and was on recursion stask
                // we have a cycle
            } else if (recursionStack[v]) {
                return true
            }
        }
        recursionStack[source] = false
        return false
    }
    for (i in 1..vertexCount) {
        if (!visited[i]) {
            if (dfs(i)) return true
        }
    }
    return false
}