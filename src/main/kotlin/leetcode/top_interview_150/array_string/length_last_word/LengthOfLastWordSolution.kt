package leetcode.top_interview_150.array_string.length_last_word

class LengthOfLastWordSolution {

    fun lengthOfLastWord(s: String): Int {
        var res = 0
        for (i in s.lastIndex downTo 0) {
            if (s[i] != ' ') {
                ++res
            }
            if (s[i] == ' ' && res != 0) {
                break
            }
        }
        return res
    }

}