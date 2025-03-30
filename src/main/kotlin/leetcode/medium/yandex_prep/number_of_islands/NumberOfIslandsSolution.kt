package leetcode.medium.yandex_prep.number_of_islands

class NumberOfIslandsSolution {

    private val directions = arrayOf(
        -1 to 0, // up
        0 to 1, // right
        1 to 0, // down
        0 to -1 // left
    )
    fun numIslands(grid: Array<CharArray>): Int {
        if (grid.isEmpty()) return 0
        val rows = grid.size
        val cols = grid[0].size
        val visited = hashSetOf<Pair<Int, Int>>()
        var islands = 0
        fun bfs(row: Int, col: Int) {
            val queue = ArrayDeque<Pair<Int, Int>>()
            queue.add(row to col)
            visited.add(row to col)
            while (queue.isNotEmpty()) {
                val (r, c) = queue.removeFirst()
                for ((dr, dc) in directions) {
                    val rr = r + dr
                    val cc = c + dc
                    if (rr in 0 until rows &&
                        cc in 0 until cols &&
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
                if (grid[row][col] == '1' && row to col !in visited) {
                    bfs(row, col)
                    ++islands
                }
            }
        }
        return islands
    }

}