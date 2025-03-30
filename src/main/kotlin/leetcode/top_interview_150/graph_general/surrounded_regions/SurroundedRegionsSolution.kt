package leetcode.top_interview_150.graph_general.surrounded_regions

class SurroundedRegionsSolution {

    fun solve(board: Array<CharArray>): Unit {
        val rows = board.size
        val cols = board[0].size
        // possible directions of dfs traversal
        val directions = arrayOf(
            -1 to 0, // up
            0 to 1, // right
            1 to 0, // down
            0 to -1 // left
        )

        fun dfs(r: Int, c: Int) {
            // mark current cell as the one that cannot be surrounded
            board[r][c] = 'T'
            // check every possible direction
            for ((dr, dc) in directions) {
                val rr = dr + r
                val cc = dc + c
                if (rr in 0 until rows && // if row is within bounds
                    cc in 0 until cols && // if col is within bounds
                    board[rr][cc] != 'T' && // if we never visited the cell
                    board[rr][cc] != 'X') { // if the cell is not X
                    board[rr][cc] = 'T' // mark the cell as visited and the one that cannot be surrounded
                    dfs(rr, cc) // continue dfs from that cell onwards
                }
            }
        }
        // regions cannot be surrounded by X if one of the O-s is on the border of
        // the grid, so we check the borders of the grid, and if we find any
        // cell containing O, then we perform dfs from that cell in all 4 directions,
        // and mark the region as T, meaning that it cannot be surrounded
        // after that we can traverse the board and replace all remaining O-s with
        // X, abd replace all T-s with O-s.

        // traverse top and bottom row
        for (i in 0 until cols) {
            if (board[0][i] == 'O') {
                dfs(0, i)
            }
            if (board[rows - 1][i] == 'O') {
                dfs(rows - 1, i)
            }
        }
        // traverse left and right cols
        for (i in 0 until rows) {
            if (board[i][0] == 'O') {
                dfs(i, 0)
            }
            if (board[i][cols - 1] == 'O') {
                dfs(i, cols - 1)
            }
        }

        // mark surrounded regions with X, and restore those
        // regions that cannot be surrounded to O-s.
        for (r in 0 until rows) {
            for (c in 0 until cols) {
                if (board[r][c] == 'O') {
                    board[r][c] = 'X'
                } else if (board[r][c] == 'T') {
                    board[r][c] = 'O'
                }
            }
        }
    }

}