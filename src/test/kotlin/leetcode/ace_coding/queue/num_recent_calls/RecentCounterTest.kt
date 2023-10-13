package leetcode.ace_coding.queue.num_recent_calls

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class RecentCounterTest {

    private lateinit var counter: RecentCounter

    @BeforeEach
    fun setup() {
        counter = RecentCounter()
    }

    @Test
    fun testCorrect() {
        assertEquals(1, counter.ping(1))
        assertEquals(2, counter.ping(100))
        assertEquals(3, counter.ping(3001))
        assertEquals(3, counter.ping(3002))
    }

}