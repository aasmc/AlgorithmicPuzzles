package leetcode.ace_coding.tries.implementation

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class TrieTest {

    private val trie = Trie()

    @Test
    fun testTrie() {
        trie.insert("apple")
        assertTrue(trie.search("apple"))
        assertFalse(trie.search("app"))
        assertTrue(trie.startsWith("app"))
        trie.insert("app")
        assertTrue(trie.search("app"))
    }

}