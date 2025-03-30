package leetcode.top_interview_150.hashmap.word_pattern

class WordPatternSolution {

    fun wordPattern(pattern: String, s: String): Boolean {
        val map = hashMapOf<Char, String>()
        val wordToChar = hashMapOf<String, Char>()
        val words = s.split("\\s+".toRegex())
        if (words.size != pattern.length) return false
        for (i in words.indices) {
            val word = words[i]
            val ch = pattern[i]
            if ((map.containsKey(ch) && map[ch] != word) ||
                (wordToChar.containsKey(word) && wordToChar[word] != ch)) {
                return false
            }
            map[ch] = word
            wordToChar[word] = ch
        }
        return true
    }

}