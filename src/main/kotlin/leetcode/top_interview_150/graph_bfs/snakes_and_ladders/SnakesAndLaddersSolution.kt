package leetcode.top_interview_150.graph_bfs.snakes_and_ladders

class SnakesAndLaddersSolution {

    fun snakesAndLadders(board: Array<IntArray>): Int {
        // how to determine row col of a square
        // e.g. board of size 6 x 6, square 7
        // row = board.lastIndex - ((square - 1) / board.size)
        // row = 5 - (7 - 1) / 6 = 5 - 1 = 4
        // To determine col, we need to know whether we are moving from right,
        // or from left.
        // fromRight = isOdd(square / board.size)
        // idx = (square - 1) % board.size
        // col = if fromRight board.lastIndex - idx else idx
        val boardSize = board.size
        val finishSquare = boardSize * boardSize
        val visited = hashSetOf<Int>()
        // stores the square and the number of moves it took us to get there
        val queue = ArrayDeque<Pair<Int, Int>>()
        queue.addLast(1 to 0)
        while (queue.isNotEmpty()) {
            val (square, moves) = queue.removeFirst()
            for (i in 1 until 7) {
                var nextSquare = square + i
                val (row, col) = extractRowCol(nextSquare - 1, boardSize)
                if (board[row][col] != -1) {
                    nextSquare = board[row][col]
                }
                // if nextSquare is our finishSquare, we need to return
                // current moves + 1, because the finishSquare will be achieved on the next move
                if (nextSquare == finishSquare) {
                    return moves + 1
                }
                // only add unvisited squares to the queue, so that we don't traverse
                // them multiple times
                if (nextSquare !in visited) {
                    visited.add(nextSquare)
                    queue.addLast(nextSquare to moves + 1)
                }
            }
        }
        return -1
    }

    private fun extractRowCol(square: Int, boardSize: Int): IntArray {
        val reverseRow = square / boardSize
        val row = boardSize - 1 - reverseRow
        // is odd means move from right to left
        val fromRight = (reverseRow % 2) == 1
        val idx = square % boardSize
        val col = if (fromRight) boardSize - 1 - idx else idx
        return intArrayOf(row, col)
    }

}