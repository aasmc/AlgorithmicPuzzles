package leetcode.medium.yandex_prep.product_of_array_except_self

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import kotlin.test.assertContentEquals

class ProductOfArrayExceptSelfSolutionTest {

    private val sut = ProductOfArrayExceptSelfSolution()

    @Test
    fun testCorrect() {
        assertContentEquals(
            intArrayOf(24, 12, 8, 6),
            sut.productExceptSelf(intArrayOf(1,2,3,4))
        )
    }

}