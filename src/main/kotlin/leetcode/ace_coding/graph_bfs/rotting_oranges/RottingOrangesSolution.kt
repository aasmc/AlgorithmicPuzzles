package leetcode.ace_coding.graph_bfs.rotting_oranges

import java.util.LinkedList

class RottingOrangesSolution {

    fun orangesRotting(grid: Array<IntArray>): Int {
        val queue = LinkedList<IntArray>()
        val visited = Array<BooleanArray>(grid.size) {
            BooleanArray(grid[0].size)
        }
        var hasFresh = false
        for (i in grid.indices) {
            for (j in grid[0].indices) {
                if (grid[i][j] == 2) {
                    queue.addLast(intArrayOf(i, j))
                    visited[i][j] = true
                } else if (grid[i][j] == 1) {
                    hasFresh = true
                }
            }
        }
        if (!hasFresh) {
            return 0
        }
        var minute = -1
        val rowDirs = intArrayOf(0, -1, 0, 1)
        val colDirs = intArrayOf(-1, 0, 1, 0)
        val maxRow = grid.size - 1
        val maxCol = grid[0].size - 1
        while (queue.isNotEmpty()) {
            ++minute
            val size = queue.size
            for (i in 0 until size) {
                val current = queue.removeFirst()
                for (k in 0..3) {
                    val nextRow = current[0] + rowDirs[k]
                    val nextCol = current[1] + colDirs[k]
                    if (nextRow in 0..maxRow &&
                        nextCol in 0..maxCol &&
                        grid[nextRow][nextCol] != 0 &&
                        !visited[nextRow][nextCol]
                    ) {
                        visited[nextRow][nextCol] = true
                        grid[nextRow][nextCol] = 2
                        queue.addLast(intArrayOf(nextRow, nextCol))
                    }
                }
            }
        }
        for (i in 0..maxRow) {
            for (j in 0..maxCol) {
                if (grid[i][j] == 1) {
                    return -1
                }
            }
        }
        return minute
    }

}