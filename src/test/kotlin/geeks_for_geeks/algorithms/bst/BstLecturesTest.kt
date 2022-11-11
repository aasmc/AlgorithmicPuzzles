package geeks_for_geeks.algorithms.bst

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

























