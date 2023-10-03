package leetcode.ace_coding.strings_arrays.product_array

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class ProductExceptSelfTest {

    @Test
    fun productExceptSelfTest() {
        val ex1 = intArrayOf(24,12,8,6)
        assertTrue(productExceptSelf(intArrayOf(1,2,3,4)).contentEquals(ex1))
        val ex2 = intArrayOf(0,0,9,0,0)
        assertTrue(productExceptSelf(intArrayOf(-1,1,0,-3,3)).contentEquals(ex2))
    }

}