package leetcode.ace_coding.tries.implementation

class Trie {

    private data class TrieNode(
        val children: MutableMap<Char, TrieNode> = hashMapOf(),
        var isEndOfWord: Boolean = false
    )

    private val root = TrieNode()

    fun insert(word: String) {
        var current = root
        for (ch in word) {
            current = current.children.computeIfAbsent(ch) { _ -> TrieNode() }
        }
        current.isEndOfWord = true
    }

    fun search(word: String): Boolean {
        var current = root
        for (ch in word) {
            current = current.children[ch] ?: return false
        }
        return current.isEndOfWord
    }

    fun startsWith(prefix: String): Boolean {
        var current = root
        for (ch in prefix) {
            current = current.children[ch] ?: return false
        }
        return true
    }

}