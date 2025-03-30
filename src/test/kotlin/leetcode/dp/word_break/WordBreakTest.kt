package leetcode.dp.word_break

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class WordBreakTest {

    @Test
    fun testCorrect() {
        assertTrue(wordBreak("aaaaaaa", listOf("aaaa","aaa")))
        assertTrue(wordBreak("goalspecial", listOf("go","goal","goals","special")))
        assertTrue(wordBreak("leetcode", listOf("leet", "code")))
        assertTrue(wordBreak("applepenapple", listOf("apple","pen")))
        assertFalse(wordBreak("catsandog", listOf("cats","dog","sand","and","cat")))
    }

}