package leetcode.top_interview_150.array_string.roman_to_integer

class RomanToIntegerSolution {
    private val romanToDigit = hashMapOf<Char, Int>(
        'I' to 1,
        'V' to 5,
        'X' to 10,
        'L' to 50,
        'C' to 100,
        'D' to 500,
        'M' to 1000
    )
    fun romanToInt(s: String): Int {

        if (s.length == 1) {
            return romanToDigit[s[0]]!!
        }
        var result = 0
        for (i in s.indices) {
            if (i < s.lastIndex && romanToDigit[s[i]]!! < romanToDigit[s[i + 1]]!!) {
                result -= romanToDigit[s[i]]!!
            } else {
                result += romanToDigit[s[i]]!!
            }
        }
        return result
    }


}