package leetcode.ace_coding.binary_search_tree.delete_node

import leetcode.ace_coding.common.TreeNode
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class DeleteNodeSolutionTest {

    val sut = DeleteNodeSolution()
    @Test
    fun testDeleteCorrect() {
        val root = buildRoot()
        val res = sut.deleteNode(root, 3)
        assertNotNull(res)

    }

    private fun buildRoot(): TreeNode? {
        val root = TreeNode(5)
        val left = TreeNode(3)
        val right = TreeNode(6)
        val leftLeft = TreeNode(2)
        val leftRight = TreeNode(4)
        val rightRight = TreeNode(7)

        root.left = left
        root.right = right
        left.left = leftLeft
        left.right = leftRight
        right.right = rightRight

        return root
    }

}