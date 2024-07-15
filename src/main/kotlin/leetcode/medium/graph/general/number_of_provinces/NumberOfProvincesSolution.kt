package leetcode.medium.graph.general.number_of_provinces

class NumberOfProvincesSolution {

    fun findCircleNum(isConnected: Array<IntArray>): Int {
        val n = isConnected.size
        val visited = BooleanArray(n)
        fun dfs(vertex: Int) {
            visited[vertex] = true
            val row = isConnected[vertex]
            for (idx in row.indices) {
                if (row[idx] != 0 && !visited[idx]) {
                    dfs(idx)
                }
            }
        }

        var count = 0
        repeat(n) { vertex ->
            if (!visited[vertex]) {
                ++count
                dfs(vertex)
            }
        }
        return count
    }

}