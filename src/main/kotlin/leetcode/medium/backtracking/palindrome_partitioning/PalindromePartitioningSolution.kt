package leetcode.medium.backtracking.palindrome_partitioning

class PalindromePartitioningSolution {

    fun partition(s: String): List<List<String>> {
        val result = mutableListOf<List<String>>()

        fun backTrack(idx: Int, partitions: List<String>) {
            if (idx == s.length) {
                result.add(partitions)
                return
            }
            for (end in idx until s.length) {
                if (isPalindrome(s, idx, end)) {
                    backTrack(end + 1, partitions + s.substring(idx, end + 1))
                }
            }
        }

        backTrack(0, listOf())

        return result
    }

    private fun isPalindrome(str: String, from: Int, to: Int): Boolean {
        var start = from
        var end = to
        while (start < end) {
            if (str[start] != str[end]) {
                return false
            }
            ++start
            --end
        }
        return true
    }

}