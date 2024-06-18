package leetcode.easy.strings.longest_common_prefix

class LongestCommonPrefixSolution {

    fun longestCommonPrefix(strs: Array<String>): String {
        strs.sort()
        val first = strs[0]
        val last = strs[strs.lastIndex]
        var idx = 0
        while (idx < first.length && idx < last.length) {
            if (first[idx] == last[idx]) {
                ++idx
            } else {
                break
            }
        }
        return first.substring(0, idx)
    }

    fun longestCommonPrefix2(strs: Array<String>): String {
        val res = StringBuilder()
        val firstString = strs[0]
        for (i in firstString.indices) {
            for (j in 1 until strs.size) {
                val currentString = strs[j]
                if (i == currentString.length ||
                    firstString[i] != currentString[i]) {
                    return res.toString()
                }
            }
            res.append(firstString[i])
        }
        return res.toString()
    }

}