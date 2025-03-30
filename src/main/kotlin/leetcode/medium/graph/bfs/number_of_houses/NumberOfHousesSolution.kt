package leetcode.medium.graph.bfs.number_of_houses

class NumberOfHousesSolution {

    fun countOfPairs(n: Int, x: Int, y: Int): IntArray {
        val maxValue = 10000
        val graph = Array(n + 1) { IntArray(n + 1) { maxValue } }
        for (i in 1 until n) {
            graph[i + 1][i] = 1
            graph[i][i + 1] = 1
        }

        graph[x][y] = 1
        graph[y][x] = 1

        for (k in 1..n) {
            for (i in 1..n) {
                for (j in 1..n) {
                    graph[i][j] = minOf(graph[i][j], graph[i][k] + graph[k][j])
                }
            }
        }
        val result = IntArray(n)
        for (i in 1..n) {
            for (j in 1..n) {
                if (i != j) {
                    val k = graph[i][j] - 1
                    result[k]++
                }
            }
        }
        return result
    }


}