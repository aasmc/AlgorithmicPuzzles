package leetcode.top_interview_150.backtracking.combinations

class CombinationsSolution {

    fun combine(n: Int, k: Int): List<List<Int>> {

        val result = mutableListOf<List<Int>>()
        fun backTrack(curNum: Int, combination: List<Int>) {
            if (combination.size == k) {
                result.add(combination)
                return
            }
            for (i in curNum..n) {
                backTrack(i + 1, combination + i)
            }
        }
        backTrack(1, listOf())

        return result
    }

}