package leetcode.easy.strings.roman_to_integer

class RomanToIntegerSolution {

    private val romanToInteger = hashMapOf<Char, Int>(
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
            return romanToInteger[s[0]]!!
        }
        var res = 0
        for(i in s.indices) {
            if (i < s.lastIndex && romanToInteger[s[i]]!! < romanToInteger[s[i + 1]]!!) {
                res -= romanToInteger[s[i]]!!
            } else {
                res += romanToInteger[s[i]]!!
            }
        }
        return res
    }

}