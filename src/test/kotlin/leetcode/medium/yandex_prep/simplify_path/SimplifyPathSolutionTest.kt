package leetcode.medium.yandex_prep.simplify_path

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class SimplifyPathSolutionTest {

    private val sut = SimplifyPathSolution()

    @Test
    fun testCorrect() {

        assertEquals("/home", sut.simplifyPath("/home/"))
        assertEquals("/", sut.simplifyPath("/../"))
        assertEquals("/home/foo", sut.simplifyPath("/home//foo/"))
        assertEquals("/home/user/Pictures", sut.simplifyPath("/home/user/Documents/../Pictures"))
        assertEquals("/.../b/d", sut.simplifyPath("/.../a/../b/c/../d/./"))
    }

}