package leetcode.medium.backtracking.word_search

class WordSearchSolution {

    fun exist(board: Array<CharArray>, word: String): Boolean {
        val rows = board.size
        val cols = board[0].size
        val path = hashSetOf<Pair<Int, Int>>()

        fun dfs(row: Int, col: Int, charIdx: Int): Boolean {
            if (charIdx == word.length) return true
            if (row < 0 || col < 0 ||
                row >= rows || col >= cols ||
                word[charIdx] != board[row][col] ||
                row to col in path
            ) {
                return false
            }
            path.add(row to col)
            val res = dfs(row + 1, col, charIdx + 1) ||
                    dfs(row - 1, col, charIdx + 1) ||
                    dfs(row, col + 1, charIdx + 1) ||
                    dfs(row, col - 1, charIdx + 1)
            path.remove(row to col)
            return res
        }
        for (row in 0 until rows) {
            for (col in 0 until cols) {
                if (dfs(row, col, 0)) {
                    return true
                }
            }
        }
        return false
    }

}