package leetcode.ace_coding.backtracking.combinations_sum

class CombinationsSumSolution {

    fun combinationSum3(k: Int, n: Int): List<List<Int>> {
        val result = mutableListOf<List<Int>>()

        fun solve(start: Int, targetSum: Int, combination: MutableList<Int>) {
            if (combination.size == k && targetSum == 0) {
                    result.add(combination.toList())
            } else {
                for (i in start..9) {
                    combination.add(i)
                    solve(i + 1, targetSum - i, combination)
                    combination.remove(i)
                }
            }
        }
        solve(1, n, mutableListOf())
        return result
    }

}