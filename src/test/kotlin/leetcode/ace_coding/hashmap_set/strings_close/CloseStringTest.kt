package leetcode.ace_coding.hashmap_set.strings_close

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class CloseStringTest {

    @Test
    fun closeStringTest() {
        assertTrue(closeStrings("abc", "bca"))
        assertTrue(closeStrings("cabbba", "abbccc"))
        assertFalse(closeStrings("a", "aa"))
    }

}