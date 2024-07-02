package leetcode.top_interview_150.binary_search_tree.construct_from_inorder_preorder

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class ConstructFromInOrderPreorderSolutionTest {

    private val sut = ConstructFromInOrderPreorderSolution()

    @Test
    fun testCorrect() {
        sut.buildTree(intArrayOf(1,2,3), intArrayOf(3,2,1))
    }

}