package leetcode.top_interview_150.array_string.longest_common_prefix

class LongestCommonPrefixSolution {

    fun longestCommonPrefix(strs: Array<String>): String {
        val res = StringBuilder()
        val firstString = strs[0]
        // for every index in the first string
        for (i in firstString.indices) {
            // for every string in the input array
            for (j in 1 until strs.size) {
                val currentString = strs[j]
                // if current index is out of bounds for the current string
                // or current string's character is not equal to the character
                // at the same position in the first string
                if (i == currentString.length || currentString[i] != firstString[i]) {
                    // return what we have accumulated so far
                    return res.toString()
                }
            }
            // don't forget to add the current character to the accumulator
            res.append(firstString[i])
        }
        return res.toString()
    }

    fun longestCommonPrefix2(strs: Array<String>): String {
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