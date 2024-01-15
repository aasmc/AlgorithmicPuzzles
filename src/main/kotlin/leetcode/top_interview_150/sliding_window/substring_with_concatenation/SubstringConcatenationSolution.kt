package leetcode.top_interview_150.sliding_window.substring_with_concatenation

class SubstringConcatenationSolution {

    fun findSubstring(s: String, words: Array<String>): List<Int> {
        val result = mutableListOf<Int>()
        permutations(words).forEach { permutation ->
            val indices = findIndices(s, permutation)
            if (indices.isNotEmpty()) {
                result.addAll(indices)
            }
        }
        return result
    }

    fun findIndices(s: String, permutation: String): List<Int> {
        val result = mutableListOf<Int>()
        val idx = s.indexOf(permutation)
        if (idx != -1) {
            return listOf(idx)
        }
        return emptyList()
//        for (i in 0..s.length - permutation.length) {
//            var equal = true
//            for (j in permutation.indices) {
//                if (s[i + j] != permutation[j]) {
//                    equal = false
//                    break
//                }
//            }
//            if (equal) {
//                result.add(i)
//            }
//        }
//        return result
    }

    fun permutations(words: Array<String>): Sequence<String> = sequence {
        val wordToCount = hashMapOf<String, Int>()
        words.forEach { word ->
            wordToCount.merge(word, 1, Int::plus)
        }
        val permutation = mutableListOf<String>()

        suspend fun SequenceScope<String>.backTrack() {
            if (words.size == permutation.size) {
                val permString = permutation.joinToString(separator = "")
                yield(permString)
            } else {
                for ((word, count) in wordToCount) {
                    if (count > 0) {
                        permutation.add(word)
                        wordToCount[word] = wordToCount[word]!! - 1
                        backTrack()
                        wordToCount[word] = wordToCount[word]!! + 1
                        permutation.removeAt(permutation.lastIndex)
                    }
                }
            }
        }
        backTrack()
    }

}