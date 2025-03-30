package leetcode.ace_coding.backtracking.letter_combination

import java.lang.StringBuilder

class LetterCombinationSolution {

    fun letterCombinations(digits: String): List<String> {
        val mapping = hashMapOf<Char, CharArray>(
            '2' to charArrayOf('a', 'b', 'c'),
            '3' to charArrayOf('d', 'e', 'f'),
            '4' to charArrayOf('g', 'h', 'i'),
            '5' to charArrayOf('j', 'k', 'l'),
            '6' to charArrayOf('m', 'n', 'o'),
            '7' to charArrayOf('p', 'q', 'r', 's'),
            '8' to charArrayOf('t', 'u', 'v'),
            '9' to charArrayOf('w', 'x', 'y', 'z')
        )

        val result = mutableListOf<String>()

        fun helper(current: String, at: Int) {
            if (current.length == digits.length) {
                result.add(current)
            } else {
                mapping[digits[at]]?.let { chars->
                    for (ch in chars) {
                        helper(current + ch, at + 1)
                    }
                }
            }
        }

        if (digits.isNotEmpty()) {
            helper("", 0)
        }
        return result
    }

}