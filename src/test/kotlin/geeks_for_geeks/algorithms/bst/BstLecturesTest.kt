package geeks_for_geeks.algorithms.bst

import com.sun.source.tree.Tree
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class BstLecturesTest {

    @Test
    fun ceilingOnLeftSideOfArray_correct() {
        val in1 = intArrayOf(2, 8, 30, 15, 25, 12)
        val ex1 = intArrayOf(-1, -1, -1, 30, 30, 15)

        val ac1 = ceilingOnLeftSideOfArray(in1)
        assertTrue(ex1.contentEquals(ac1))

        val in2 = intArrayOf(30, 20, 10)
        val ex2 = intArrayOf(-1, 30, 20)

        val ac2 = ceilingOnLeftSideOfArray(in2)
        assertTrue(ex2.contentEquals(ac2))

        val in3 = intArrayOf(10, 20, 30)
        val ex3 = intArrayOf(-1, -1, -1)

        val ac3 = ceilingOnLeftSideOfArray(in3)
        assertTrue(ex3.contentEquals(ac3))

        val in4 = intArrayOf(6, 18, 4, 5)
        val ex4 = intArrayOf(-1, -1, 6, 6)

        val ac4 = ceilingOnLeftSideOfArray(in4)
        assertTrue(ex4.contentEquals(ac4))

    }

    @Test
    fun findKthSmallestElement_correct() {
        val root = buildAugmentedBST()
        val ex1 = 10
        val ac1 = findKthSmallestElement(root, 2)
        assertEquals(ex1, ac1)

        assertNull(findKthSmallestElement(root, 100))

        val ex2 = 50
        val ac2 = findKthSmallestElement(root, 5)
        assertEquals(ex2, ac2)

        val ex3 = 70
        val ac3 = findKthSmallestElement(root, 7)
        assertEquals(ex3, ac3)
    }

    @Test
    fun checkForBSTEfficientOne_correct() {
        assertTrue(checkForBSTEfficientOne(buildBSTree()))
        assertFalse(checkForBSTEfficientOne(buildNonBST()))
    }

    @Test
    fun checkForBSTEfficientTwo_correct() {
        assertTrue(checkForBSTEfficientTwo(buildBSTree()))
        assertFalse(checkForBSTEfficientTwo(buildNonBST()))
    }

    @Test
    fun findAndSwapTwoElementsInArrayToMakeItSorted_correct() {
        val in1 = intArrayOf(4, 60, 10, 20, 8, 80, 100)
        findAndSwapTwoElementsInArrayToMakeItSorted(in1)
        for (i in 1 until in1.size) {
            assertTrue(in1[i] >= in1[i - 1])
        }

        val in2 = intArrayOf(4, 8, 10, 60, 20, 70, 80)
        findAndSwapTwoElementsInArrayToMakeItSorted(in2)
        for (i in 1 until in2.size) {
            assertTrue(in2[i] >= in2[i - 1])
        }
    }

    @Test
    fun fixTreeToBSTWithTwoSwappedNodes_correct() {
        val (incorrect, correct) = buildBSTsForSwapping()
        val result = fixTreeToBSTWithTwoSwappedNodes(incorrect)
        assertEquals(result, correct)
    }

    private fun buildBSTsForSwapping(): List<TreeNode<Int>> {
        val first = TreeNode(
            data = 20,
            left = TreeNode(60, left = TreeNode(4), right = TreeNode(10)),
            right = TreeNode(80, left = TreeNode(8), right = TreeNode(100))
        )
        val second = TreeNode(
            data = 20,
            left = TreeNode(8, left = TreeNode(4), right = TreeNode(10)),
            right = TreeNode(80, left = TreeNode(60), right = TreeNode(100))
        )
        return listOf(first, second)
    }

    private fun buildBSTree(): TreeNode<Int> {
        return TreeNode(
            data = 80,
            left = TreeNode(
                data = 70,
                left = TreeNode(60),
                right = TreeNode(75)
            ),
            right = TreeNode(
                data = 90,
                left = TreeNode(85),
                right = TreeNode(100)
            )
        )
    }

    private fun buildNonBST(): TreeNode<Int> {
        return TreeNode(
            data = 20,
            left = TreeNode(
                data = 8,
            ),
            right = TreeNode(
                data = 30,
                left = TreeNode(18),
                right = TreeNode(35)
            )
        )
    }

    private fun buildAugmentedBST(): LCountNode<Int> {
        return LCountNode(
            data = 50,
            lCount = 4,
            left = LCountNode(
                data = 20,
                lCount = 2,
                left = LCountNode(
                    data = 10,
                    lCount = 1,
                    left = LCountNode(
                        data = 5
                    )
                ),
                right = LCountNode(
                    data = 40
                )
            ),
            right = LCountNode(
                data = 100,
                lCount = 3,
                left = LCountNode(
                    data = 70,
                    lCount = 1,
                    left = LCountNode(60),
                    right = LCountNode(80)
                ),
                right = LCountNode(120)
            )
        )
    }

}

























