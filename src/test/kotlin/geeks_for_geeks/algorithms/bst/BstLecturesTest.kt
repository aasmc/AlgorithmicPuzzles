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

    @Test
    fun findPairSum_correct() {
        val (positive, negative) = buildBSTForSumPair()
        assertTrue(findPairSumUsingHashMap(positive, 33))
        assertFalse(findPairSumUsingHashMap(negative, 49))
    }

    @Test
    fun findPairSumUsingHashSet_correct() {
        val (positive, negative) = buildBSTForSumPair()
        assertTrue(findPairSumUsingHashSet(positive, 33))
        assertFalse(findPairSumUsingHashSet(negative, 49))
    }

    @Test
    fun verticalSumInBinaryTree_correct() {
        val pairs = buildBSTforVerticalSum()
        val first = pairs[0]
        val second = pairs[1]

        val firstTree = first.first
        val firstExpected = first.second
        val firstActual = verticalSumInBinaryTree(firstTree)
        assertEquals(firstExpected, firstActual)

        val secondTree = second.first
        val secondExpected = second.second
        val secondActual = verticalSumInBinaryTree(secondTree)
        assertEquals(secondExpected, secondActual)
    }

    @Test
    fun verticalOrderTraversal_correct() {
        val pairs = buildBSTforVerticalTraversal()
        val first = pairs[0]
        val second = pairs[1]

        val firstTree = first.first
        val firstExpected = first.second
        val firstActual = verticalOrderTraversal(firstTree)
        assertEquals(firstExpected, firstActual)

        val secondTree = second.first
        val secondExpected = second.second
        val secondActual = verticalOrderTraversal(secondTree)
        assertEquals(secondExpected, secondActual)
    }

    @Test
    fun topViewOfBinaryTree_correct() {
        val pairs = buildBinaryTreeForTopView()
        val first = pairs[0]
        val second = pairs[1]

        val firstTree = first.first
        val firstExpected = first.second
        val firstActual = topViewOfBinaryTree(firstTree)
        assertEquals(firstExpected, firstActual)

        val secondTree = second.first
        val secondExpected = second.second
        val secondActual = topViewOfBinaryTree(secondTree)
        assertEquals(secondExpected, secondActual)
    }

    private fun buildBinaryTreeForTopView(): List<Pair<TreeNode<Int>, List<Int>>> {
        val first = TreeNode(
            10,
            left = TreeNode(20, left = TreeNode(30), right = TreeNode(40)),
            right = TreeNode(50, left = TreeNode(60), right = TreeNode(70))
        )
        val firstResult = listOf(30, 20, 10, 50, 70)
        val firstPair = first to firstResult

        val second = TreeNode(
            1,
            left = TreeNode(2, right = TreeNode(4, right = TreeNode(5))),
            right = TreeNode(3)
        )
        val secondResult = listOf(2, 1, 3)
        val secondPair = second to secondResult
        return listOf(firstPair, secondPair)
    }

    private fun buildBSTforVerticalTraversal(): List<Pair<TreeNode<Int>, List<List<Int>>>> {
        val first = TreeNode(
            10,
            left = TreeNode(20),
            right = TreeNode(30, left = TreeNode(40), right = TreeNode(50))
        )
        val firstResult = listOf(listOf(20), listOf(10, 40), listOf(30), listOf(50))
        val firstPair = first to firstResult

        val second = TreeNode(
            1,
            left = TreeNode(2, left = TreeNode(4), right = TreeNode(5)),
            right = TreeNode(
                3,
                left = TreeNode(6),
                right = TreeNode(
                    7,
                    left = TreeNode(
                        8,
                        left = TreeNode(9)
                    )
                )
            )
        )
        val secondResult = listOf(listOf(4), listOf(2), listOf(1,5,6,9), listOf(3, 8), listOf(7))
        val secondPair = second to secondResult
        return listOf(firstPair, secondPair)
    }

    private fun buildBSTforVerticalSum(): List<Pair<TreeNode<Int>, List<Int>>> {
        val first = TreeNode(
            10,
            left = TreeNode(20, left = TreeNode(5), right = TreeNode(15)),
            right = TreeNode(30)
        )
        val firstResult = listOf(5, 20, 25, 30)
        val firstPair = first to firstResult

        val second = TreeNode(
            10,
            right = TreeNode(25),
            left = TreeNode(
                15,
                left = TreeNode(35, left = TreeNode(40)),
                right = TreeNode(20, right = TreeNode(75, right = TreeNode(80)))
            )
        )
        val secondResult = listOf(40, 35, 15, 30, 100, 80)
        val secondPair = second to secondResult
        return listOf(firstPair, secondPair)
    }

    private fun buildBSTForSumPair(): List<TreeNode<Int>> {
        val positive = TreeNode(
            data = 10,
            left = TreeNode(8, left = TreeNode(4), right = TreeNode(9)),
            right = TreeNode(
                20, left = TreeNode(11), right = TreeNode(
                    data = 30,
                    left = TreeNode(25)
                )
            )
        )

        val negative = TreeNode(
            data = 20,
            left = TreeNode(8),
            right = TreeNode(40, left = TreeNode(35))
        )
        return listOf(positive, negative)
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

























