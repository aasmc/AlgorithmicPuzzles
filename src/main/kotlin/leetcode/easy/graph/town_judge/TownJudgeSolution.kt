package leetcode.easy.graph.town_judge

class TownJudgeSolution {

    fun findJudge(n: Int, trust: Array<IntArray>): Int {
        val inDegree = IntArray(n + 1)
        val outDegree = IntArray(n + 1)
        trust.forEach { (from, to) ->
            inDegree[to]++
            outDegree[from]++
        }
        for (i in 1..n) {
            if (outDegree[i] == 0 && inDegree[i] == n - 1) {
                return i
            }
        }
        return -1
    }

    fun findJudge2(n: Int, trust: Array<IntArray>): Int {
        val grid = Array(n) { IntArray(n) }
        trust.forEach { (r, c) ->
            grid[r - 1][c - 1] = 1
        }
        for (col in 0 until n) {
            var trustCount = 0
            for (row in 0 until n) {
                if (grid[row][col] == 1) {
                    ++trustCount
                }
            }
            if (trustCount == n - 1) {
                var candidateRow = 0
                for (row in 0 until n) {
                    if (grid[row][col] == 0) {
                        candidateRow = row
                        break
                    }
                }
                var found = true
                for (c in 0 until n) {
                    if (grid[candidateRow][c] != 0) {
                        found = false
                        break
                    }
                }
                if (found) {
                    return candidateRow + 1
                }
            }
        }
        return -1
    }

}