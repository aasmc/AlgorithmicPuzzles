package leetcode.easy.yandex_prep.range_sum_of_bst

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class RangeSumOfBstSolutionTest {

    private val sut = RangeSumOfBstSolution()

    @Test
    fun testCorrect() {
        val root = RangeSumOfBstSolution.TreeNode(
            10
        )
        root.left = RangeSumOfBstSolution.TreeNode(5)
        root.left!!.left = RangeSumOfBstSolution.TreeNode(3)
        root.left!!.right = RangeSumOfBstSolution.TreeNode(7)
        root.right = RangeSumOfBstSolution.TreeNode(15)
        root.right!!.right = RangeSumOfBstSolution.TreeNode(18)

        val res = sut.rangeSumBST(root, 7, 15)
        assertEquals(32, res)
    }

}