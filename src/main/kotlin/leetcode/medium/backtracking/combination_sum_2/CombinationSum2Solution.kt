package leetcode.medium.backtracking.combination_sum_2

class CombinationSum2Solution {

    fun combinationSum2(candidates: IntArray, target: Int): List<List<Int>> {
        val result = mutableListOf<List<Int>>()
        candidates.sort()
        fun dfs(idx: Int, combination: List<Int>, sum: Int) {
            if (sum == target) {
                result.add(combination)
                return
            }
            if (idx == candidates.size || sum > target) return
            // we have two choices: either include current number in the combination, or
            // don't include it. In the first case, we simply add current number to the
            // combination
            dfs(idx + 1, combination + candidates[idx], sum + candidates[idx])
            var nextIdx = idx + 1
            // in the second case we skip all numbers equal to the current number, since
            // we don't want to include any of them, because it will create duplicates
            // in the result
            while (nextIdx < candidates.size && candidates[nextIdx] == candidates[idx]) {
                ++nextIdx
            }
            dfs(nextIdx, combination, sum)
        }
        dfs(0, listOf(), 0)
        return result
    }

}