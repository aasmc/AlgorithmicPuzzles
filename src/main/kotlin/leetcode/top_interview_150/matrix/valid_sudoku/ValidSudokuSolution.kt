package leetcode.top_interview_150.matrix.valid_sudoku

class ValidSudokuSolution {

    fun isValidSudoku(board: Array<CharArray>): Boolean {

        val colSet = mutableSetOf<Char>()
        val rowSet = mutableSetOf<Char>()
        val cube = mutableSetOf<Char>()
        for(col in 0..8) {
            colSet.clear()
            rowSet.clear()
            cube.clear()

            for(row in 0..8) {
                val c = board[col][row]
                val r = board[row][col]

                if(c != '.' && !colSet.add(c)) return false
                if(r != '.' && !rowSet.add(r)) return false

                val colindex = 3*(col%3) + row%3
                val rowindex = 3*(col/3) + row/3

                val b = board[colindex][rowindex]

                if(b != '.' && !cube.add(b)) return false
            }
        }

        return true
    }

    data class Square(
        val row: Int,
        val col: Int
    )

    fun isValidSudoku2(board: Array<CharArray>): Boolean {
        val columns = initRowColDict()
        val rows = initRowColDict()
        val squares = initSquaresDict()
        for (row in 0 until 9) {
            for (col in 0 until 9) {
                val ch = board[row][col]
                if (ch != '.') {
                    if (hasDuplicates(board, columns, rows, squares, row, col)) {
                        return false
                    }
                    columns[col]!!.add(ch)
                    rows[row]!!.add(ch)
                    squares[Square(row / 3, col / 3)]!!.add(ch)
                }
            }
        }
        return true
    }

    private fun hasDuplicates(
        board: Array<CharArray>,
        cols: Map<Int, Set<Char>>,
        rows: Map<Int, Set<Char>>,
        squares: Map<Square, Set<Char>>,
        row: Int,
        col: Int
    ): Boolean {
        val ch = board[row][col]
        return rows[row]!!.contains(ch) ||
                cols[col]!!.contains(ch) ||
                squares[Square(row / 3, col / 3)]!!.contains(ch)
    }

    private fun initRowColDict(): Map<Int, MutableSet<Char>> = hashMapOf<Int, MutableSet<Char>>(
        0 to hashSetOf(),
        1 to hashSetOf(),
        2 to hashSetOf(),
        3 to hashSetOf(),
        4 to hashSetOf(),
        5 to hashSetOf(),
        6 to hashSetOf(),
        7 to hashSetOf(),
        8 to hashSetOf(),
        9 to hashSetOf(),
    )

    private fun initSquaresDict(): Map<Square, MutableSet<Char>> = hashMapOf(
        Square(0, 0) to hashSetOf(),
        Square(0, 1) to hashSetOf(),
        Square(0, 2) to hashSetOf(),
        Square(1, 0) to hashSetOf(),
        Square(1, 1) to hashSetOf(),
        Square(1, 2) to hashSetOf(),
        Square(2, 0) to hashSetOf(),
        Square(2, 1) to hashSetOf(),
        Square(2, 2) to hashSetOf(),
    )

}