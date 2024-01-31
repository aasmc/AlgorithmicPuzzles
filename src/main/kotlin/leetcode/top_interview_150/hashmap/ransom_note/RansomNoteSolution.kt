package leetcode.top_interview_150.hashmap.ransom_note

class RansomNoteSolution {

    fun canConstruct(ransomNote: String, magazine: String): Boolean {
        val charToCnt = hashMapOf<Char, Int>()
        magazine.forEach { ch ->
            charToCnt.merge(ch, 1, Int::plus)
        }
        for (ch in ransomNote) {
            charToCnt.merge(ch, -1, Int::plus)
            if (charToCnt[ch]!! < 0) {
                return false
            }
        }
        return true
    }

}