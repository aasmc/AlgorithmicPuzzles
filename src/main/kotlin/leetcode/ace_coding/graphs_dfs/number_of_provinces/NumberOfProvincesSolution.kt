package leetcode.ace_coding.graphs_dfs.number_of_provinces

class NumberOfProvincesSolution {
    fun findCircleNum(isConnected: Array<IntArray>): Int {
        val visited = BooleanArray(isConnected.size)
        fun dfs(i: Int) {
            visited[i] = true
            val row = isConnected[i]
            for (j in row.indices) {
                if (row[j] != 0 && !visited[j]) {
                    dfs(j)
                }
            }
        }

        var count = 0
        for (i in isConnected.indices) {
            if (!visited[i]) {
                ++count
                dfs(i)
            }
        }
        return count
    }
}