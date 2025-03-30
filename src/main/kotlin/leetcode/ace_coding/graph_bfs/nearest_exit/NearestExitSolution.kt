package leetcode.ace_coding.graph_bfs.nearest_exit

import java.util.LinkedList

class NearestExitSolution {


    fun nearestExit(maze: Array<CharArray>, entrance: IntArray): Int {
        val queue = LinkedList<IntArray>()
        queue.addLast(entrance)
        var step = 0
        val maxRow = maze.size - 1
        val maxCol = maze[0].size - 1
        val visited = Array<BooleanArray>(maze.size) {
            BooleanArray(maze[0].size)
        }
        visited[entrance[0]][entrance[1]] = true
        val rowDirs = intArrayOf(0, -1, 0, 1)
        val colDirs = intArrayOf(-1, 0, 1, 0)
        while (queue.isNotEmpty()) {
            val size = queue.size
            ++step
            for (i in 0 until size) {
                val current = queue.removeFirst()
                visited[current[0]][current[1]] = true
                for (k in 0..3) {
                    val nextRow = rowDirs[k] + current[0]
                    val nextCol = colDirs[k] + current[1]
                    if (nextRow in 0..maxRow &&
                        nextCol in 0..maxCol &&
                        maze[nextRow][nextCol] != '+' &&
                        !visited[nextRow][nextCol]
                    ) {
                        visited[nextRow][nextCol] = true
                        if (nextRow == 0 || nextRow == maxRow ||
                            nextCol == 0 || nextCol == maxCol) {
                            return step
                        } else {
                            queue.addLast(intArrayOf(nextRow, nextCol))
                        }
                    }
                }
            }
        }
        return -1
    }

}