package leetcode.top_interview_150.array_string.integer_to_roman

class IntegerToRomanSolution {

    private val lst = listOf<Pair<String, Int>>(
        "I" to 1,
        "IV" to 4,
        "V" to 5,
        "IX" to 9,
        "X" to 10,
        "XL" to 40,
        "L" to 50,
        "XC" to 90,
        "C" to 100,
        "CD" to 400,
        "D" to 500,
        "CM" to 900,
        "M" to 1000
    )

    fun intToRoman(num: Int): String {
        val sb = StringBuilder()
        var current = num
        for (i in lst.lastIndex downTo 0) {
            val pair = lst[i]
            val roman = pair.first
            val digit = pair.second
            val count = current / digit
            repeat(count) {
                sb.append(roman)
            }
            current %= digit
        }
        return sb.toString()
    }


}