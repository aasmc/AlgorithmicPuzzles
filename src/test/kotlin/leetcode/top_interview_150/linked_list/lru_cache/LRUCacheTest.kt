package leetcode.top_interview_150.linked_list.lru_cache

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class LRUCacheTest {

    private val sut = LRUCache(2)

    @Test
    fun testCorrect() {
        sut.put(1, 1)
        sut.put(2, 2)
        assertEquals(1, sut.get(1))
        sut.put(3, 3)
        assertEquals(-1, sut.get(2))
        sut.put(4, 4)
        assertEquals(-1, sut.get(1))
        assertEquals(3, sut.get(3))
        assertEquals(4, sut.get(4))
    }

}