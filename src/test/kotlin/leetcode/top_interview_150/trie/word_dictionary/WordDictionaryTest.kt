package leetcode.top_interview_150.trie.word_dictionary

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class WordDictionaryTest {

    private val sut = WordDictionary()

    @Test
    fun testCorrect() {
        sut.addWord("a")

        assertTrue(sut.search("."))
    }

}