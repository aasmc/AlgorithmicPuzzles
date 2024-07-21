package leetcode.top_interview_150.trie.word_dictionary

class WordDictionary() {

    data class Node(
        val children: MutableMap<Char, Node> = hashMapOf(),
        var isEndOfWord: Boolean = false
    )

    private val root = Node()

    fun addWord(word: String) {
        var current = root
        word.forEach { ch ->
            current = current.children.computeIfAbsent(ch){_ -> Node()}
        }
        current.isEndOfWord = true
    }

    fun search(word: String): Boolean {
        fun dfs(idx: Int, node: Node): Boolean {
            var current = node
            for (i in idx until word.length) {
                val ch = word[i]
                if (ch != '.') {
                    current = current.children[ch] ?: return false
                } else {
                    for (child in current.children.values) {
                        if (dfs(i + 1, child)) {
                            return true
                        }
                    }
                    return false
                }
            }
            return current.isEndOfWord
        }
        return dfs(0, root)
    }


}