package leetcode.top_interview_150.sliding_window.substring_with_concatenation

class SubstringConcatenationSolution {

    fun findSubstring(s: String, words: Array<String>): List<Int> {
        if (words.isEmpty()) return emptyList()
        val wordLength = words[0].length
        val concatLength = wordLength * words.size
        val result = mutableListOf<Int>()
        val wordToCount = hashMapOf<String, Int>()
        words.forEach { word ->
            wordToCount.merge(word, 1, Int::plus)
        }
        for (i in 0 until wordLength) {
            var start = i
            var end = start + concatLength
            if (end > s.length) break
            val used = hashMapOf<String, Int>()
            s.substring(start, end).chunked(wordLength).forEach { w ->
                used.merge(w, 1, Int::plus)
            }
            if (used == wordToCount) {
                result.add(start)
            }
            while (end <= s.length - wordLength) {
                val oldWord = s.substring(start, start + wordLength)
                if (used[oldWord]!! > 1) {
                    used.merge(oldWord, -1, Int::plus)
                } else {
                    used.remove(oldWord)
                }
                val newWord = s.substring(end, end + wordLength)
                used.merge(newWord, 1, Int::plus)
                start += wordLength
                end += wordLength

                if (used == wordToCount) {
                    result.add(start)
                }
            }
        }
        return result
    }


}