package leetcode.top_interview_150.backtracking.generate_parentheses

class GenerateParenthesesSolution {

    fun generateParenthesis(n: Int): List<String> {
        val result = mutableListOf<String>()
        fun backTrack(str: String, openCount: Int, closedCount: Int) {
            if (openCount == n && closedCount == n) {
                result.add(str)
                return
            }
            if (openCount == n && closedCount < n) {
                backTrack("$str)", openCount, closedCount + 1)
            } else if (openCount == closedCount) {
                backTrack("$str(", openCount + 1, closedCount)
            } else if (openCount > closedCount) {
                backTrack("$str(", openCount + 1, closedCount)
                backTrack("$str)", openCount, closedCount + 1)
            }
        }
        backTrack("", 0, 0)
        return result
    }

}