package geeks_for_geeks.algorithms.bst

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class BstTreeTest {
    private val root = BstTree.Node<Int>(
        data = 20,
        left = BstTree.Node(
            data = 10,
            left = BstTree.Node(5),
            right = BstTree.Node(15)
        ),
        right = BstTree.Node(
            data = 30,
            left = BstTree.Node(25),
            right = BstTree.Node(35)
        )
    )
    private val tree = BstTree(root)

    @Test
    fun testSearchRecursively_correct() {
        assertTrue(tree.searchRecursive(35))
        assertTrue(tree.searchRecursive(25))
        assertTrue(tree.searchRecursive(30))
        assertTrue(tree.searchRecursive(5))

        assertFalse(tree.searchRecursive(100))
        assertFalse(tree.searchRecursive(11))
        assertFalse(tree.searchRecursive(17))
        assertFalse(BstTree<Int>(null).searchRecursive(17))

    }

    @Test
    fun testSearchIteratively_correct() {
        assertTrue(tree.searchIterative(35))
        assertTrue(tree.searchIterative(25))
        assertTrue(tree.searchIterative(30))
        assertTrue(tree.searchIterative(5))

        assertFalse(tree.searchIterative(100))
        assertFalse(tree.searchIterative(11))
        assertFalse(tree.searchIterative(17))
        assertFalse(BstTree<Int>(null).searchIterative(17))
    }

    @Test
    fun insertRecursive_correct() {
        val t = BstTree<Int>(null)
        t.insertRecursive(20)
        t.insertRecursive(30)
        t.insertRecursive(40)
        t.insertRecursive(50)

        assertTrue(t.searchIterative(20))
        assertTrue(t.searchIterative(30))
        assertTrue(t.searchIterative(40))
        assertTrue(t.searchIterative(50))

        assertFalse(t.searchIterative(11))
        assertFalse(t.searchIterative(12))
        assertFalse(t.searchIterative(13))
        assertFalse(t.searchIterative(16))
        assertFalse(t.searchIterative(19))
    }

    @Test
    fun insertIterative_correct() {
        val t = BstTree<Int>(null)
        t.insertIterative(20)
        t.insertIterative(30)
        t.insertIterative(40)
        t.insertIterative(50)

        assertTrue(t.searchIterative(20))
        assertTrue(t.searchIterative(30))
        assertTrue(t.searchIterative(40))
        assertTrue(t.searchIterative(50))

        assertFalse(t.searchIterative(11))
        assertFalse(t.searchIterative(12))
        assertFalse(t.searchIterative(13))
        assertFalse(t.searchIterative(16))
        assertFalse(t.searchIterative(19))
    }

    @Test
    fun deleteRecursive_correct() {
        tree.deleteRecursive(20)
        assertFalse(tree.searchIterative(20))
        assertTrue(tree.searchIterative(15))
        assertEquals(25, tree.root!!.data)

        tree.deleteRecursive(15)
        assertFalse(tree.searchIterative(15))
        assertTrue(tree.searchIterative(35))

        tree.deleteRecursive(25)
        assertEquals(30, tree.root!!.data)
    }
}





























