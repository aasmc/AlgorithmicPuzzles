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

}