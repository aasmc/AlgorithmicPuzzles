package leetcode.top_interview_150.stack.simplify_path

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class SimplifyPathSolutionTest {

    private val sut = SimplifyPathSolution()

    @Test
    fun testCorrect() {
        assertEquals("/.../b/d", sut.simplifyPath("/.../a/../b/c/../d/./"))
        assertEquals("/home", sut.simplifyPath("/home/"))
        assertEquals("/home/foo", sut.simplifyPath("/home//foo/"))
        assertEquals("/home/user/Pictures", sut.simplifyPath("/home/user/Documents/../Pictures"))
        assertEquals("/", sut.simplifyPath("/../"))
    }

}