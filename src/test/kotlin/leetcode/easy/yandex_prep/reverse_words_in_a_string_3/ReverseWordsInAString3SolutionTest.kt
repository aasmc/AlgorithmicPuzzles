package leetcode.easy.yandex_prep.reverse_words_in_a_string_3

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class ReverseWordsInAString3SolutionTest {

    private val sut = ReverseWordsInAString3Solution()

    @Test
    fun testCorrect() {

        assertEquals("s'teL ekat edoCteeL tsetnoc", sut.reverseWords("Let's take LeetCode contest"))
        assertEquals("Mr Ding", sut.reverseWords("rM gniD"))

    }

}