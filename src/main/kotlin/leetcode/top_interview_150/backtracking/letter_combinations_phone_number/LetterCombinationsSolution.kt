package leetcode.top_interview_150.backtracking.letter_combinations_phone_number

class LetterCombinationsSolution {

    private val digitToLetters = hashMapOf(
        '2' to charArrayOf('a', 'b', 'c'),
        '3' to charArrayOf('d', 'e', 'f'),
        '4' to charArrayOf('g', 'h', 'i'),
        '5' to charArrayOf('j', 'k', 'l'),
        '6' to charArrayOf('m', 'n', 'o'),
        '7' to charArrayOf('p', 'q', 'r', 's'),
        '8' to charArrayOf('t', 'u', 'v'),
        '9' to charArrayOf('w', 'x', 'y', 'z'),
    )

    fun letterCombinations(digits: String): List<String> {
        if (digits.isEmpty()) {
            return emptyList()
        }
        val result = mutableListOf<String>()

        // idx - index we are at in the digits string
        // prefix - combination that we have built so far
        fun backtrack(idx: Int, prefix: String) {
            if (prefix.length == digits.length) {
                result.add(prefix)
                return
            }
            val digit = digits[idx]
            val letters = digitToLetters[digit]!!
            for (letter in letters) {
                backtrack(idx + 1, "$prefix$letter")
            }
        }
        backtrack(0, "")
        return result
    }

}