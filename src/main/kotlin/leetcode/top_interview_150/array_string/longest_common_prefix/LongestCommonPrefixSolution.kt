package leetcode.top_interview_150.array_string.longest_common_prefix

class LongestCommonPrefixSolution {

    fun longestCommonPrefix(strs: Array<String>): String {
        strs.sort()
        var idx = 0
        val first = strs.first()
        val last = strs.last()
        while (idx < first.length) {
            if (first[idx] == last[idx]) {
                idx++
            } else {
                break
            }
        }
        return first.substring(0, idx)
    }

}