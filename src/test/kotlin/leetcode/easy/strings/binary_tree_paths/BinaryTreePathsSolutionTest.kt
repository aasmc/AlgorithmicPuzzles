package leetcode.easy.strings.binary_tree_paths

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class BinaryTreePathsSolutionTest {

    private val sut = BinaryTreePathsSolution()

    @Test
    fun testCorrect() {
        val root = BinaryTreePathsSolution.TreeNode(1)
        val two = BinaryTreePathsSolution.TreeNode(2)
        val three = BinaryTreePathsSolution.TreeNode(3)
        val five = BinaryTreePathsSolution.TreeNode(5)

        root.left = two
        root.right = three
        two.right = five

        val res = sut.binaryTreePaths(root)
            .sortedBy { it.length }

        assertEquals("1->3", res[0])
        assertEquals("1->2->5", res[1])
    }

}