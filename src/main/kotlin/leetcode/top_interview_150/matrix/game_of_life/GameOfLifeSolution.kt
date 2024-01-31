package leetcode.top_interview_150.matrix.game_of_life

class GameOfLifeSolution {


    private val ONE_TO_ZERO = 1
    private val ZERO_TO_ONE = 2
    private val ONE_TO_ONE = 3


    fun gameOfLife(board: Array<IntArray>): Unit {
        val numRows = board.size
        val numCols = board[0].size
        val initialOnes = hashSetOf(ONE_TO_ZERO, ONE_TO_ONE)

        fun countLiveNeighbours(row: Int, col: Int): Int {
            var cnt = 0
            for (r in row - 1..row + 1) {
                for (c in col - 1..col + 1) {
                    if ((r == row && c == col) || r < 0 || c < 0 ||
                        r >= numRows || c >= numCols
                    ) {
                        continue
                    }
                    if (board[r][c] in initialOnes) {
                        ++cnt
                    }
                }
            }
            return cnt
        }

        for (row in board.indices) {
            for (col in board[0].indices) {
                val neighbours = countLiveNeighbours(row, col)
                if (board[row][col] == 1) { // we are transitioning from [1]
                    if (neighbours == 2 || neighbours == 3) { // if the number of neighbours
                        // is enough for the cell to stay alive, then update the cell
                        board[row][col] = ONE_TO_ONE
                    } // if the number of cells is not enough, we leave the value as
                    // it is, i.e. [1], and it will signify that the cell died, but was
                    // previously alive.
                } else if (neighbours == 3) { // we are transitioning from [0]
                    // and in this case the cell has enough neighbours to resurrect
                    board[row][col] = ZERO_TO_ONE
                    // if the [0] cell doesn't have enough neighbours to resurrect,
                    // then its value will stay the same - [0], i.e. transition from 0 to 0
                }
            }
        }

        for (row in board.indices) {
            for (col in board[0].indices) {
                val curr = board[row][col]
                if (curr == ONE_TO_ZERO) {
                    board[row][col] = 0
                } else if (curr == ONE_TO_ONE || curr == ZERO_TO_ONE) {
                    board[row][col] = 1
                }
            }
        }

    }

}