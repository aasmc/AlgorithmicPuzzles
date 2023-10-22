package leetcode.ace_coding.binary_search.spells_and_potions

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class SpellsAndPotionsSolutionTest {

    private val sut = SpellsAndPotionsSolution()
    @Test
    fun testCorrect() {
        val spells = intArrayOf(5,1,3)
        val potions = intArrayOf(1,2,3,4,5)
        val exp = intArrayOf(4,0,3)
        val ac = sut.successfulPairs(spells, potions, 7)
        assertTrue(exp.contentEquals(ac))

        val sp2 = intArrayOf(3,1,2)
        val p2 = intArrayOf(8,5,8)
        val exp2 = intArrayOf(2,0,2)
        val ac2 = sut.successfulPairs(sp2, p2, 16)
        assertTrue(exp2.contentEquals(ac2))
    }

}