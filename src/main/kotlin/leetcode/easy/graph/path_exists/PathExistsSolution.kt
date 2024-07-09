package leetcode.easy.graph.path_exists

class PathExistsSolution {

    fun validPath(n: Int, edges: Array<IntArray>, source: Int, destination: Int): Boolean {
        val graph = hashMapOf<Int, MutableList<Int>>()
        edges.forEach { (from, to) ->
            graph.merge(from, mutableListOf(to)) { old, _ ->
                old.add(to)
                old
            }
            graph.merge(to, mutableListOf(from)){old, _ ->
                old.add(from)
                old
            }
        }
        val visited = BooleanArray(n)
        var found = false
        fun dfs(vertex: Int) {
            if (vertex == destination) {
                found = true
            }
            if (found) return
            visited[vertex] = true
            val neighbours = graph[vertex] ?: emptyList()
            for (neighbour in neighbours) {
                if (!visited[neighbour]) {
                    visited[neighbour] = true
                    dfs(neighbour)
                }
            }
        }
        dfs(source)
        return found
    }

}