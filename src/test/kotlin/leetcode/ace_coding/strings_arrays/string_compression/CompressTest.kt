package leetcode.ace_coding.strings_arrays.string_compression

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class CompressTest {

    @Test
    fun stringCompressionTest() {
        val i1 = charArrayOf('a', 'a', 'b', 'b', 'c', 'c', 'c')
        val r1 = compress(i1)
        assertEquals(6, r1)

        assertEquals(1, compress(charArrayOf('a')))
        assertEquals(4, compress(charArrayOf('a','b','b','b','b','b','b','b','b','b','b','b','b')))
        val i2 = charArrayOf('a','a','a','b','b','a','a')
        val r2 = compress(i2)
        assertEquals(6, r2)
        
        val i3 = charArrayOf('a','b','c')
        val r3 = compress(i3)
        assertEquals(3, r3)
        
        val i4 = charArrayOf('a','a','a','a','a','b')
        val r4 = compress(i4)
        assertEquals(3, r4)
    }

}