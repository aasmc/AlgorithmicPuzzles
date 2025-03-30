package geeks_for_geeks.algorithms.hashing

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import java.lang.IllegalStateException
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

internal class MyHashTest {

    private val myHash: MyHash = MyHash(7)

    @Test
    fun test_insert_search() {
        myHash.insert(49)
        myHash.insert(52)
        assertTrue { myHash.search(52) }
        assertFalse { myHash.search(32) }
        assertTrue { myHash.search(49) }
        assertEquals(2, myHash.size)
        myHash.print()
    }

    @Test
    fun test_erase_after_insert() {
        myHash.insert(49)
        myHash.insert(32)
        myHash.insert(33)
        assertEquals(3, myHash.size)

        myHash.erase(32)
        assertTrue { myHash.search(49) }
        assertFalse { myHash.search(32) }
        assertEquals(2, myHash.size)
        myHash.print()
    }

    @Test
    fun test_erase_throws() {
        assertEquals(0, myHash.size)
        assertThrows<IllegalArgumentException> {
            myHash.erase(10)
        }
    }

    @Test
    fun test_search_with_collisions() {
        myHash.insert(7)
        myHash.insert(14)
        myHash.insert(21)
        myHash.insert(28)
        assertEquals(4, myHash.size)
        assertTrue { myHash.search(7) }
        assertTrue { myHash.search(14) }
        assertTrue { myHash.search(21) }
        assertTrue { myHash.search(28) }
    }

    @Test
    fun test_insert_throws_when_too_many_elements() {
        assertThrows<IllegalStateException> {
            assertEquals(0, myHash.size)
            myHash.insert(10)
            myHash.insert(10)
            myHash.insert(10)
            myHash.insert(10)
            myHash.insert(10)
            myHash.insert(10)
            myHash.insert(10)
            assertEquals(7, myHash.size)
            myHash.insert(10)
        }
    }

    @Test
    fun test_erase_with_collisions() {
        myHash.insert(7)
        myHash.insert(14)
        myHash.insert(21)
        myHash.insert(28)
        myHash.insert(32)
        myHash.insert(33)
        myHash.insert(35)
        assertEquals(7, myHash.size)

        myHash.erase(35)
        assertEquals(6, myHash.size)
        assertTrue {
           myHash.search(28)
        }
        assertFalse {
            myHash.search(35)
        }
    }

}