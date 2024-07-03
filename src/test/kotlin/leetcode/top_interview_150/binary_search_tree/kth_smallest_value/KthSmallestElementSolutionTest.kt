package leetcode.top_interview_150.binary_search_tree.kth_smallest_value

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class KthSmallestElementSolutionTest {

    private val sut = KthSmallestElementSolution()

    @Test
    fun testCorrect() {

        val root = KthSmallestElementSolution.TreeNode(5)
        root.left = KthSmallestElementSolution.TreeNode(3)
        root.left!!.left = KthSmallestElementSolution.TreeNode(2)
        root.left!!.left!!.left = KthSmallestElementSolution.TreeNode(1)
        root.left!!.right = KthSmallestElementSolution.TreeNode(4)
        root.right = KthSmallestElementSolution.TreeNode(6)

        val ex = sut.kthSmallest(root, 1)
        assertEquals(1, ex)

    }

}