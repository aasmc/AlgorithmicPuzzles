package leetcode.medium.backtracking.max_length_of_contatenated_str

class MaxLengthOfConcatenatedStrSolution {

    fun maxLength(arr: List<String>): Int {

        fun dfs(idx: Int, concatenated: String): Int {
            if (!isUnique(concatenated)) {
                return 0
            }
            if (idx == arr.size) {
                return concatenated.length
            }
            return maxOf(
                concatenated.length,
                // add current string to the concatenation
                dfs(idx + 1, "$concatenated${arr[idx]}"),
                // don't add current string to the concatenation
                dfs(idx + 1, concatenated)
            )
        }
        return dfs(0, "")
    }

    private fun isUnique(str: String): Boolean {
        val set = hashSetOf<Char>()
        for (ch in str) {
            if (!set.add(ch)) {
                return false
            }
        }
        return true
    }

}