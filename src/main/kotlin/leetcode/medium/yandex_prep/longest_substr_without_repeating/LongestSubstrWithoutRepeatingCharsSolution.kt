package leetcode.medium.yandex_prep.longest_substr_without_repeating

class LongestSubstrWithoutRepeatingCharsSolution {

    fun lengthOfLongestSubstring(s: String): Int {
        var result = 0
        var left = 0
        val unique = hashSetOf<Char>()

        for (right in s.indices) {
            while (s[right] in unique) {
                unique.remove(s[left++])
            }
            unique.add(s[right])
            result = maxOf(result, unique.size)
        }

        return result
    }

}