package leetcode.top_interview_150.backtracking.combination_sum

class CombinationSumSolution {

    fun combinationSum(candidates: IntArray, target: Int): List<List<Int>> {
        val result = mutableListOf<List<Int>>()

        // idx - index of the candidate we are considering right now
        // combination - combination of numbers without current candidate
        // sum - sum of the combination of numbers
        fun dfs(idx: Int, combination: List<Int>, sum: Int) {
            if (sum == target) {
                result.add(combination)
                return
            }
            if (idx >= candidates.size || sum > target) {
                return
            }
            // case 1: we add current number to the combination, in this case,
            // we continue the dfs with the same index, because we can add numbers
            // multiple times to the combination
            dfs(idx, combination + candidates[idx], sum + candidates[idx])
            // case 2: we don't add current number to the combination, therefore
            // we continue the dfs from the next index so that current number couldn't
            // be included to the combination
            dfs(idx + 1, combination, sum)
        }
        dfs(0, listOf(), 0)
        return result
    }

}