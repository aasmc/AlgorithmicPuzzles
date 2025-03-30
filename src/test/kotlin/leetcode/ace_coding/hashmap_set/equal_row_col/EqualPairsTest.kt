package leetcode.ace_coding.hashmap_set.equal_row_col

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class EqualPairsTest {

    @Test
    fun equalPairsTest() {
        val m = arrayOf(
            intArrayOf(3,2,1),
            intArrayOf(1,7,6),
            intArrayOf(2,7,7),
        )
        assertEquals(1, equalPairs(m))

    }

}
