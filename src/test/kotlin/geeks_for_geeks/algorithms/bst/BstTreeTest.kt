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

    @Test
    fun floor_correct() {
        val expected = 25
        val actual = tree.floor(27) ?: throw Exception()
        assertEquals(expected, actual)

        val ex2 = 30
        val ac2 = tree.floor(30) ?: throw Exception()
        assertEquals(ex2, ac2)

        val ac3 = tree.floor(4)
        assertNull(ac3)

        val ex4 = 35
        val ac4 = tree.floor(100) ?: throw Exception()
        assertEquals(ex4, ac4)
    }

    @Test
    fun ceiling_correct() {
        val ex1 = 30
        val ac1 = tree.ceiling(26)
        assertEquals(ex1, ac1)

        val ac2 = tree.ceiling(36)
        assertNull(ac2)

        val ex3 = 5
        val ac3 = tree.ceiling(1)
        assertEquals(ex3, ac3)

        val ex4 = 10
        val ac4 = tree.ceiling(10)
        assertEquals(ex4, ac4)
    }
}





























