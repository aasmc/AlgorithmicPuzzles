package leetcode.top_interview_150.trie.implement_trie

class Trie() {

    data class TrieNode(
        var isEndOfWord: Boolean = false,
        val children: MutableMap<Char, TrieNode> = hashMapOf()
    )

    private val root = TrieNode()

    fun insert(word: String) {
        var current = root
        word.forEach { ch ->
            current = current.children.computeIfAbsent(ch) { _ -> TrieNode() }
        }
        current.isEndOfWord = true
    }

    fun search(word: String): Boolean {
        var current = root
        word.forEach { ch ->
            current = current.children[ch] ?: return false
        }
        return current.isEndOfWord
    }

    fun startsWith(prefix: String): Boolean {
        var current = root
        prefix.forEach { ch ->
            current = current.children[ch] ?: return false
        }
        return true
    }

}