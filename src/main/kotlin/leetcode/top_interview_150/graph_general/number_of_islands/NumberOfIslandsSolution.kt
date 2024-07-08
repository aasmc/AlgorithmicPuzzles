package leetcode.top_interview_150.graph_general.number_of_islands

class NumberOfIslandsSolution {

    fun numIslands(grid: Array<CharArray>): Int {
        if (grid.isEmpty()) return 0
        val rows = grid.size
        val cols = grid[0].size
        val visited = hashSetOf<Pair<Int, Int>>()
        var islands = 0

        val directions = arrayOf(
            1 to 0,   // up
            -1 to 0, // down
            0 to 1,  // right
            0 to -1  // left
        )

        fun bfs(r: Int, c: Int) {
            val queue = ArrayDeque<Pair<Int, Int>>()
            visited.add(r to c)
            queue.add(r to c)

            while (queue.isNotEmpty()) {
                val (row, col) = queue.removeFirst()
                for ((dr, dc) in directions) {
                    val rr = row + dr
                    val cc = col + dc
                    if ((rr in 0 until rows) &&
                        (cc in 0 until cols) &&
                        grid[rr][cc] == '1' &&
                        rr to cc !in visited) {
                        queue.add(rr to cc)
                        visited.add(rr to cc)
                    }
                }
            }
        }

        for (row in 0 until rows) {
            for (col in 0 until cols) {
                if (grid[row][col] == '1' && !visited.contains(row to col)) {
                    bfs(row, col)
                    ++islands
                }
            }
        }
        return islands
    }

}