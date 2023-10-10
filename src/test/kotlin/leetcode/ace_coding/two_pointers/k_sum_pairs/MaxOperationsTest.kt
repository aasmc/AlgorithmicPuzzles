package leetcode.ace_coding.two_pointers.k_sum_pairs

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class MaxOperationsTest {

    @Test
    fun maxOperationsTestCorrect() {
        assertEquals(2, maxOperations(intArrayOf(1,2,3,4), 5))
        assertEquals(1, maxOperations(intArrayOf(3,1,3,4,3), 6))
    }

}