package leetcode.easy.sliding_window.longest_nice_substring

class LongestNiceSubstringSolution {

    fun longestNiceSubstring(s: String): String {
        fun helper(str: String): String {
            // if we have less than 2 chars, then the substring is not nice by definition
            if (str.length < 2) return ""
            // stores bits of all uppercase chars
            var upperCase = 0
            // stores bits of all lowercase chars
            var lowerCase = 0
            for (ch in str) {
                // to set bit of the current char we need to compute the index of the bit
                // to do this we subtract either 'a' or 'A' from this char to get its
                // position in the ASCII table. After that we shift 1 by that position
                // and perform bitwise or with corresponding integer. Bitwise or in this case
                // will set the bit to 1 at correct position for current char.
                if (ch.isUpperCase()) {
                    upperCase = upperCase or (1 shl (ch - 'A'))
                } else {
                    lowerCase = lowerCase or (1 shl (ch - 'a'))
                }
            }
            for (i in str.indices) {
                val ch = str[i]
                // here we compute the index of the bit of that char by the same logic
                // as described above
                val bitIdx = if (ch.isUpperCase()) ch - 'A' else ch - 'a'
                // here we extract that bit by shifting it to the right by the
                // bitIdx steps. After that we perform bitwise and with that bit
                // That means, that if the bit was set, the result will be 1 otherwise - 0.
                val lowerBit = (lowerCase shr bitIdx) and 1
                val upperBit = (upperCase shr bitIdx) and 1
                // this line checks if both bits are set. To do this we use bitwise xor,
                // because xor will be 0 only if both bits are set or both bits are unset
                // (which will never happen, because either a lowercase char is present, or
                // uppercase char is present, or both uppercase and lowercase chars are present).
                // And if both bits are set, this means, that we have both: uppercase and lowercase
                // char in current string. In this case we continue our loop and try to move to the next
                // char.
                if ((lowerBit xor upperBit) == 0) continue
                // if we don't have both uppercase and lowercase char in current substring
                // then divide it into two substrings excluding current character and
                // try to find a nice substring in them. Since we have to return the largest
                // of the possible substrings and if there are multiple substrings, we need to
                // return the first one, we always return left substring if it is greater than or equal
                // to the right substring
                val left = helper(str.substring(0, i))
                val right = helper(str.substring(i + 1))
                return if (left.length >= right.length) left else right
            }
            // if we reached this line, this means our substring is nice, so return it
            return str
        }
        return helper(s)
    }

    fun longestNiceSubstring1(s: String): String {
        fun helper(str: String): String {
            if (str.length < 2) {
                return ""
            }
            val unique = str.toHashSet()
            for (i in str.indices) {
                if (!unique.contains(str[i].lowercaseChar()) ||
                    !unique.contains(str[i].uppercaseChar())
                ) {
                    val left = helper(str.substring(0, i))
                    val right = helper(str.substring(i + 1))
                    return if (left.length >= right.length) left else right
                }
            }
            return str
        }
        return helper(s)
    }

    fun longestNiceSubstring2(s: String): String {
        fun helper(from: Int, to: Int): String {
            if (from == to) {
                return ""
            }
            val unique = s.substring(from, to).toHashSet()
            for (i in from until to) {
                if ((!unique.contains(s[i].uppercaseChar())) || (!unique.contains(s[i].lowercaseChar()))) {
                    val left = helper(from, i)
                    val right = helper(i + 1, to)
                    return if (left.length >= right.length) left else right
                }
            }
            val res = s.substring(from, to)
            return res
        }
        return helper(0, s.length)
    }

}