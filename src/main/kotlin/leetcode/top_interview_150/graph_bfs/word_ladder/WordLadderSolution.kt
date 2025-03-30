package leetcode.top_interview_150.graph_bfs.word_ladder

class WordLadderSolution {

    fun ladderLength(beginWord: String, endWord: String, wordList: List<String>): Int {
        val wordToTransformations = buildTransformationsMap2(wordList + beginWord)
        val queue = ArrayDeque<String>()
        queue.addLast(beginWord)
        val visited = hashSetOf(beginWord)
        var level = 1
        while (queue.isNotEmpty()) {
            val size = queue.size
            repeat(size) {
                val currentWord = queue.removeFirst()
                if (currentWord == endWord) {
                    return level
                }

                for (pattern in getPatterns(currentWord)) {
                    for (neighbour in wordToTransformations.getOrDefault(pattern, emptyList())) {
                        if (neighbour !in visited) {
                            visited.add(neighbour)
                            queue.addLast(neighbour)
                        }
                    }
                }
            }
            ++level
        }
        return 0
    }

    private fun buildTransformationsMap2(
        wordList: List<String>
    ): Map<String, MutableList<String>> {
        val wordToTransformations = hashMapOf<String, MutableList<String>>()
        wordList.forEach { word ->
            for (pattern in getPatterns(word)) {
                wordToTransformations.merge(pattern, mutableListOf(word)) {old, _ ->
                    old.add(word)
                    old
                }
            }
        }
        return wordToTransformations
    }

    private fun getPatterns(word: String): List<String> {
        val patterns = mutableListOf<String>()
        for (i in word.indices) {
            val pattern = word.substring(0, i) + '*' + word.substring(i + 1)
            patterns.add(pattern)
        }
        return patterns
    }

    fun ladderLength2(beginWord: String, endWord: String, wordList: List<String>): Int {
        val wordToTransformations = buildTransformationsMap(wordList, beginWord)
        val queue = ArrayDeque<Pair<String, Int>>()
        queue.addLast(beginWord to 1)
        val visited = hashSetOf(beginWord)

        while (queue.isNotEmpty()) {
            val (currentWord, transformations) = queue.removeFirst()
            if (currentWord == endWord) {
                return transformations
            }
            for (word in wordToTransformations.getOrDefault(currentWord, emptyList())) {
                if (word !in visited) {
                    visited.add(word)
                    queue.addLast(word to transformations + 1)
                }
            }
        }
        return 0
    }

    private fun buildTransformationsMap(
        wordList: List<String>,
        beginWord: String
    ): Map<String, MutableList<String>> {
        val wordToTransformations = hashMapOf<String, MutableList<String>>()
        for (i in wordList.indices) {
            for (j in i + 1 until wordList.size) {
                if (canTransform(wordList[i], wordList[j])) {
                    wordToTransformations.merge(wordList[i], mutableListOf(wordList[j])) { old, _ ->
                        old.add(wordList[j])
                        old
                    }
                    wordToTransformations.merge(wordList[j], mutableListOf(wordList[i])) { old, _ ->
                        old.add(wordList[i])
                        old
                    }
                }
            }
        }

        wordList.forEach { word ->
            if (canTransform(word, beginWord)) {
                wordToTransformations.merge(word, mutableListOf(beginWord)) { old, _ ->
                    old.add(beginWord)
                    old
                }
                wordToTransformations.merge(beginWord, mutableListOf(word)) { old, _ ->
                    old.add(word)
                    old
                }
            }
        }
        return wordToTransformations
    }

    private fun canTransform(word: String, newWord: String): Boolean {
        var cnt = 0
        for (i in word.indices) {
            if (word[i] != newWord[i]) {
                ++cnt
            }
            if (cnt > 1) {
                return false
            }
        }
        return cnt == 1
    }

}