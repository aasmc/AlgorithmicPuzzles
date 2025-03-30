package leetcode.medium.graph.bfs.min_height_trees

class MinHeightTreesSolution {

    fun findMinHeightTrees(n: Int, edges: Array<IntArray>): List<Int> {
        val graph = hashMapOf<Int, MutableList<Int>>()
        edges.forEach { (u, v) ->
            graph.merge(u, mutableListOf(v)) {old, _ ->
                old.add(v)
                old
            }
            graph.merge(v, mutableListOf(u)) {old, _ ->
                old.add(u)
                old
            }
        }

        // stores the degrees of every node
        val degree = IntArray(n)
        var leaves = mutableListOf<Int>()
        for (i in 0 until n) {
            degree[i] = graph.getOrDefault(i, emptyList()).size
            if (degree[i] <= 1) {
                leaves.add(i)
                degree[i] = 0
            }
        }
        var count = leaves.size
        while (count < n) {
            val newLeaves = mutableListOf<Int>()
            for (node in leaves) {
                for (nei in graph.getOrDefault(node, emptyList())) {
                    degree[nei]--
                    if (degree[nei] == 1) {
                        newLeaves.add(nei)
                    }
                }
                degree[node] = 0
            }
            count += newLeaves.size
            leaves = newLeaves
        }
        return leaves
    }

}