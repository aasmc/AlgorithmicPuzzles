package leetcode.top_interview_150.sliding_window.longest_substring_without_repeating

class LongestSubstringWithoutRepeatingCharactersSolution {

    fun lengthOfLongestSubstring(s: String): Int {
        val unique = hashSetOf<Char>()
        var res = 0
        var left = 0
        for(right in s.indices) {
            while (s[right] in unique) {
                unique.remove(s[left++])
            }
            unique.add(s[right])
            res = maxOf(res, right - left + 1)
        }
        return res
    }

}