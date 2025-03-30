package leetcode.top_interview_150.binary_search_tree.max_depth

import leetcode.top_interview_150.binary_tree_general.max_depth.MaxDepthSolution
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class MaxDepthSolutionTest {

    private val sut = MaxDepthSolution()

    @Test
    fun testCorrect() {

        val root = MaxDepthSolution.TreeNode(3)
        val left = MaxDepthSolution.TreeNode(9)
        root.left = left
        val right = MaxDepthSolution.TreeNode(20)
        root.right = right
        val rightLeft = MaxDepthSolution.TreeNode(15)
        val rightRight = MaxDepthSolution.TreeNode(7)
        right.left = rightLeft
        right.right = rightRight

        val depth = sut.maxDepth(root)
        assertEquals(3, depth)
    }

}