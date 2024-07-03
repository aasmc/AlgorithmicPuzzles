package leetcode.top_interview_150.binary_search_tree.next_right_pointer

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class NextRightPointerSolutionTest {

    private val sut = NextRightPointerSolution()

    @Test
    fun testCorrect() {

        val root = NextRightPointerSolution.Node(1)
        root.left = NextRightPointerSolution.Node(2)
        root.left!!.left = NextRightPointerSolution.Node(4)
        root.left!!.right = NextRightPointerSolution.Node(5)
        root.right = NextRightPointerSolution.Node(3)
        root.right!!.right = NextRightPointerSolution.Node(7)

        sut.connect(root)
    }

}