package leetcode.top_interview_150.backtracking.n_queens_2

class NQueens2Solution {

    fun totalNQueens(n: Int): Int {
        val columns = hashSetOf<Int>()
        val positiveDiagonal = hashSetOf<Int>()
        val negativeDiagonal = hashSetOf<Int>()
        var count = 0

        fun backTrack(row: Int) {
            if (row == n) {
                ++count
                return
            }
            for (col in 0 until n) {
                if (col in columns || (row + col) in positiveDiagonal || (row - col) in negativeDiagonal) {
                    continue
                }
                columns.add(col)
                positiveDiagonal.add(row + col)
                negativeDiagonal.add(row - col)
                backTrack(row + 1)
                columns.remove(col)
                positiveDiagonal.remove(row + col)
                negativeDiagonal.remove(row - col)
            }
        }
        backTrack(0)
        return count
    }

}