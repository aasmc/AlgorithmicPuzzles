package leetcode.top_interview_150.array_string.test_justification

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class TextJustificationSolutionTest {

    private val sut = TextJustificationSolution()

    @Test
    fun testCorrect() {
        val input1 = arrayOf("This", "is", "an", "example", "of", "text", "justification.")
        val ex1 = listOf(
            "This    is    an",
            "example  of text",
            "justification.  "
        )
        assertEquals(ex1, sut.fullJustify(input1, 16))

        val input2 = arrayOf("What","must","be","acknowledgment","shall","be")
        val ex2 = listOf(
            "What   must   be",
            "acknowledgment  ",
            "shall be        "
        )
        assertEquals(ex2, sut.fullJustify(input2, 16))

        val input3 = arrayOf("Science","is","what","we","understand","well","enough","to","explain","to","a","computer.","Art","is","everything","else","we","do")
        val ex3 = listOf(
            "Science  is  what we",
            "understand      well",
            "enough to explain to",
            "a  computer.  Art is",
            "everything  else  we",
            "do                  "
        )
        assertEquals(ex3, sut.fullJustify(input3, 20))
    }

}