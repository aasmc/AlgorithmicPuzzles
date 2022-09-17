package geeks_for_geeks.algorithms.tree

import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach

internal class MyTreeTest {

    private lateinit var root: MyTree.Node<Int>
    private lateinit var tree: MyTree<Int>

    @BeforeEach
    fun createRoot() {
        root = MyTree.Node(10)
        root.left = MyTree.Node(20)
        root.right = MyTree.Node(30, left = MyTree.Node(40), right = MyTree.Node(50))
        tree = MyTree(root)
    }

    @AfterEach
    fun clearTree() {
        tree.clear()
    }

    @Test
    fun inorderTraversalRecursive_correct() {
        val expected = listOf(20, 10, 40, 30, 50)
        val result = mutableListOf<Int>()
        tree.inorderTraversalRecursive { data ->
            result.add(data)
        }
        assertEquals(expected, result.toList())
    }

    @Test
    fun preorderTraversalRecursive_correct() {
        val expected = listOf(10, 20, 30, 40, 50)
        val result = mutableListOf<Int>()
        tree.preOrderTraversalRecursive { data ->
            result.add(data)
        }
        assertEquals(expected, result.toList())

    }

    @Test
    fun postorderTraversalRecursive_correct() {
        val expected = listOf(20, 40, 50, 30, 10)
        val result = mutableListOf<Int>()
        tree.postOrderTraversalRecursive { data ->
            result.add(data)
        }
        assertEquals(expected, result.toList())

    }

    @Test
    fun height_correct() {
        val expected = 3
        assertEquals(expected, tree.height())
    }

    @Test
    fun nodesAtKDistance_correct() {
        val expected = listOf(20, 30)
        val result = mutableListOf<Int>()
        tree.nodesAtKDistance(1) {
            result.add(it)
        }
        assertEquals(expected, result.toList())
    }

    @Test
    fun levelOrderTraversal_correct() {
        val expected = listOf(10, 20, 30, 40, 50)
        val result = mutableListOf<Int>()
        tree.levelOrderTraversal {
            result.add(it)
        }
        assertEquals(expected, result.toList())
    }


    @Test
    fun levelOrderTraversalLineByLine_correct() {
        val expected = listOf(listOf(10), listOf(20, 30), listOf(40, 50))
        val result = tree.levelOrderLineByLine()
        assertEquals(expected, result.toList())
    }

    @Test
    fun size_correct() {
        assertEquals(5, tree.size())
    }

    @Test
    fun maxValue_correct() {
        val expected = 50
        assertEquals(expected, tree.maxValue())
    }

    @Test
    fun leftViewOfTreeRecursive_correct() {
        val expected = listOf<Int>(10, 20, 40)
        assertEquals(expected, tree.leftViewOfTreeRecursive())
    }

    @Test
    fun leftViewOfTreeIterative_correct() {
        val expected = listOf<Int>(10, 20, 40)
        assertEquals(expected, tree.leftViewOfTreeIterative())
    }

    @Test
    fun isChildrenSumCompliant_correct() {
        assertFalse(tree.isChildrenSumCompliant())

        val r = MyTree.Node<Int>(20)
        val rl = MyTree.Node(8)
        val rr = MyTree.Node(12)
        val rll = MyTree.Node(3)
        val rlr = MyTree.Node(5)
        r.left = rl
        r.right = rr
        rl.left = rll
        rl.right = rlr
        val t = MyTree(r)
        assertTrue(t.isChildrenSumCompliant())
    }

    @Test
    fun isHeightBalanced_correct() {
        assertTrue(tree.isHeightBalanced())
        val r = MyTree.Node<Int>(20)
        val rl = MyTree.Node(8)
        val rr = MyTree.Node(12)
        val rll = MyTree.Node(3)
        val rlr = MyTree.Node(5)
        r.left = rl
        r.left?.left = rr
        rl.left?.left?.left = rll
        rl.left?.left?.left?.left = rlr
        val t = MyTree(r)
        assertFalse(t.isHeightBalanced())
    }

    @Test
    fun maxWidth_correct() {
        assertEquals(2, tree.maxWidth())
        val r = MyTree.Node(1)
        val rl = MyTree.Node(2)
        val rr = MyTree.Node(3)
        r.left = rl
        r.right = rr
        val rll = MyTree.Node(4)
        val rlr = MyTree.Node(5)
        rl.left = rll
        rl.right = rlr
        val rrl = MyTree.Node(6)
        val rrr = MyTree.Node(7)
        rr.left = rrl
        rr.right = rrr
        val t = MyTree(r)
        assertEquals(4, t.maxWidth())
    }

    @Test
    fun toDoublyLinkedList_correct() {
        val head = tree.toDoublyLinkedList()
        assertEquals(20, head!!.data)
        assertEquals(10, head.right!!.data)
        assertEquals(40, head.right!!.right!!.data)
        assertEquals(30, head.right!!.right!!.right!!.data)
        assertEquals(50, head.right!!.right!!.right!!.right!!.data)
        assertNull(head.right!!.right!!.right!!.right!!.right)
    }

    @Test
    fun constructFromInorderAndPreOrder_correct() {
        val inOrder1 = arrayOf(20, 10, 30)
        val preOrder1 = arrayOf(10, 20, 30)
        val expected = MyTree.Node(10);
        expected.left = MyTree.Node(20)
        expected.right = MyTree.Node(30)

        val res1 = MyTree.constructFromInorderAndPreOrder(inOrder1, preOrder1)
        assertEquals(expected.data, res1.data)
        assertEquals(expected.left!!.data, res1.left!!.data)
        assertEquals(expected.right!!.data, res1.right!!.data)

        val inOrder2 = arrayOf(40, 20, 50, 10, 30, 80, 70, 90)
        val preOrder2 = arrayOf(10, 20, 40, 50, 30, 70, 80, 90)
        val resRoot = MyTree.constructFromInorderAndPreOrder(inOrder2, preOrder2)
        val expectedTree = MyTree(resRoot)
        val inOrderRes = mutableListOf<Int>()
        expectedTree.inorderTraversalRecursive {
            inOrderRes.add(it)
        }
        val preOrderRes = mutableListOf<Int>()
        expectedTree.preOrderTraversalRecursive {
            preOrderRes.add(it)
        }

        assertTrue(inOrder2.toIntArray().contentEquals(inOrderRes.toIntArray()))
        assertTrue(preOrder2.toIntArray().contentEquals(preOrderRes.toIntArray()))
    }

    @Test
    fun constructFromInorderAndPreOrderWithHash_correct() {
        val inOrder1 = arrayOf(20, 10, 30)
        val preOrder1 = arrayOf(10, 20, 30)
        val expected = MyTree.Node(10);
        expected.left = MyTree.Node(20)
        expected.right = MyTree.Node(30)

        val res1 = MyTree.constructFromInorderAndPreOrderWithHash(inOrder1, preOrder1)
        assertEquals(expected.data, res1.data)
        assertEquals(expected.left!!.data, res1.left!!.data)
        assertEquals(expected.right!!.data, res1.right!!.data)

        val inOrder2 = arrayOf(40, 20, 50, 10, 30, 80, 70, 90)
        val preOrder2 = arrayOf(10, 20, 40, 50, 30, 70, 80, 90)
        val resRoot = MyTree.constructFromInorderAndPreOrderWithHash(inOrder2, preOrder2)
        val expectedTree = MyTree(resRoot)
        val inOrderRes = mutableListOf<Int>()
        expectedTree.inorderTraversalRecursive {
            inOrderRes.add(it)
        }
        val preOrderRes = mutableListOf<Int>()
        expectedTree.preOrderTraversalRecursive {
            preOrderRes.add(it)
        }

        assertTrue(inOrder2.toIntArray().contentEquals(inOrderRes.toIntArray()))
        assertTrue(preOrder2.toIntArray().contentEquals(preOrderRes.toIntArray()))
    }

    @Test
    fun levelOrderTraversalSpiralForm_correct() {
        val expected = listOf<Int>(10, 30, 20, 40, 50)
        val result = mutableListOf<Int>()
        tree.levelOrderTraversalSpiralFormUsingStack {
            result.add(it)
        }

        assertEquals(expected, result.toList())
    }
}





















