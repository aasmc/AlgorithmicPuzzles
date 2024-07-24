package leetcode.top_interview_150.trie.word_search_2

class AnotherWordSearch2Solution {
    data class TrieNode(
        val children: MutableMap<Char, TrieNode> = hashMapOf(),
        var isEndOfWord: Boolean = false
    ) {

        fun addWord(word: String) {
            var current = this
            word.forEach { ch ->
                current = current.children.computeIfAbsent(ch) {_ -> TrieNode()}
            }
            current.isEndOfWord = true
        }
    }

    fun findWords(board: Array<CharArray>, words: Array<String>): List<String> {

        val root = TrieNode()
        words.forEach(root::addWord)

        val rows = board.size
        val cols = board[0].size
        val visited = hashSetOf<Pair<Int, Int>>()
        val result = hashSetOf<String>()

        fun dfs(r: Int, c: Int, node: TrieNode, prefix: String) {
            if (r < 0 || c < 0 ||
                r >= rows || c >= cols ||
                r to c in visited ||
                !node.children.containsKey(board[r][c])) {
                return
            }
            visited.add(r to c)
            val newNode = node.children[board[r][c]]!!
            val newWord = "$prefix${board[r][c]}"
            if (newNode.isEndOfWord) {
                result.add(newWord)
            }

            dfs(r - 1, c, newNode, newWord)
            dfs(r + 1, c, newNode, newWord)
            dfs(r, c - 1, newNode, newWord)
            dfs(r, c + 1, newNode, newWord)

            visited.remove(r to c)
        }

        for (r in 0 until rows) {
            for (c in 0 until cols) {
                dfs(r, c, root, "")
            }
        }

        return result.toList()
    }

}

class WordSearch2Solution {

    class Trie {
        data class TrieNode(
            val children: MutableMap<Char, TrieNode> = hashMapOf(),
            var isEndOfWord: Boolean = false
        )

        private val root = TrieNode()

        fun addWord(word: String) {
            var current = root
            word.forEach { ch ->
                current = current.children.computeIfAbsent(ch) { _ -> TrieNode() }
            }
            current.isEndOfWord = true
        }

        fun isPrefix(prefix: String): Boolean {
            var current = root
            prefix.forEach { ch ->
                current = current.children[ch] ?: return false
            }
            return true
        }

        fun containsWord(word: String): Boolean {
            var current = root
            word.forEach { ch ->
                current = current.children[ch] ?: return false
            }
            return current.isEndOfWord
        }
    }

    fun findWords(board: Array<CharArray>, words: Array<String>): List<String> {
        val trie = Trie()
        words.forEach(trie::addWord)
        val rows = board.size
        val cols = board[0].size
        val path = hashSetOf<Pair<Int, Int>>()
        val result = hashSetOf<String>()
        fun dfs(r: Int, c: Int, prefix: String) {
            if (r < 0 || c < 0 ||
                r >= rows || c >= cols ||
                r to c in path
            ) {
                return
            }

            val candidate = "$prefix${board[r][c]}"

            if (!trie.isPrefix(candidate)) {
                return
            }

            if (trie.containsWord(candidate)) {
                result.add(candidate)
            }

            path.add(r to c)
            dfs(r + 1, c, candidate)
            dfs(r - 1, c, candidate)
            dfs(r, c + 1, candidate)
            dfs(r, c - 1, candidate)
            path.remove(r to c)
        }
        for (r in 0 until rows) {
            for (c in 0 until cols) {
                dfs(r, c, "")
            }
        }
        return result.toList()
    }

}