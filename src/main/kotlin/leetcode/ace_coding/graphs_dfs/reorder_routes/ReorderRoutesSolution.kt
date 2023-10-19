package leetcode.ace_coding.graphs_dfs.reorder_routes

class ReorderRoutesSolution {



    fun minReorder(n: Int, connections: Array<IntArray>): Int {
        data class Neighbour(
            val city: Int,
            val sign: Int
        )

        val adj = hashMapOf<Int, MutableList<Neighbour>>()
        for (conn in connections) {
            // direct edge from parent to child, which will have to be reversed to allow
            // the child to reach parent
            adj.computeIfAbsent(conn[0]) { ArrayList() }.add(Neighbour(conn[1], 1))
            // our artificial edge from child to parent
            adj.computeIfAbsent(conn[1]) { ArrayList() }.add(Neighbour(conn[0], 0))
        }

        var count = 0
        val visited = BooleanArray(n)
        fun dfs(node: Int) {
            if (!adj.containsKey(node)) {
                return
            }
            visited[node] = true
            // check every neighbour of current city
            for (neig in adj[node]!!) {
                if (!visited[neig.city]) {
                    count += neig.sign
                    dfs(neig.city)
                }
            }
        }
        dfs(0)
        return count
    }


}