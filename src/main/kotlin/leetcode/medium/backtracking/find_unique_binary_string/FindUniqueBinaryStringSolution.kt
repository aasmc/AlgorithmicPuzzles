package leetcode.medium.backtracking.find_unique_binary_string

class FindUniqueBinaryStringSolution {

    fun findDifferentBinaryString(nums: Array<String>) = buildString {
        nums.forEachIndexed { idx, str ->
            if (str[idx] == '0') append('1') else append('0')
        }
    }

    fun findDifferentBinaryString2(nums: Array<String>): String {
        val set = nums.toHashSet()
        val size = nums.size
        fun dfs(idx: Int, str: String): String {
            if (idx == size) {
                if (str !in set) {
                    return str
                }
                return ""
            }
            val zero = dfs(idx + 1, "$str${0}")
            if (zero.isNotEmpty()) return zero
            return dfs(idx + 1, "$str${1}")
        }
        return dfs(0, "")
    }
}