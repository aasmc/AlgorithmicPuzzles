package leetcode.top_interview_150.backtracking.word_search

class WordSearchSolution {

    fun exist(board: Array<CharArray>, word: String): Boolean {
        val rows = board.size
        val cols = board[0].size
        val path = hashSetOf<Pair<Int, Int>>()

        fun dfs(r: Int, c: Int, wordIdx: Int): Boolean {
            if (wordIdx == word.length) {
                return true
            }
            if (r < 0 || c < 0 ||
                r >= rows || c >= cols ||
                board[r][c] != word[wordIdx] ||
                r to c in path) {
                return false
            }
            path.add(r to c)
            val newIdx = wordIdx + 1
            val exists = dfs(r + 1, c, newIdx) ||
                    dfs(r - 1, c, newIdx) ||
                    dfs(r, c + 1, newIdx) ||
                    dfs(r, c - 1, newIdx)
            path.remove(r to c)
            return exists
        }
        for (r in 0 until rows) {
            for (c in 0 until cols) {
                if (dfs(r, c, 0)) {
                    return true
                }
            }
        }
        return false
    }

}