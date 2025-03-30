package leetcode.ace_coding.strings_arrays.flowers

import org.junit.jupiter.api.Test
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class FlowersTest {

    @Test
    fun placeFlowersTest() {
//        assertTrue(canPlaceFlowers(intArrayOf(1,0,0,0,1), 1))
        assertTrue(canPlaceFlowers(intArrayOf(0,0,1,0,0), 1))
        assertFalse(canPlaceFlowers(intArrayOf(1,0,0,0,1), 2))
    }

}