package leetcode.ace_coding.bit_manipulation.min_flips

class MinFlipsSolution {

    fun minFlips(a: Int, b: Int, c: Int): Int {
        var aa = a
        var bb = b
        var cc = c
        var count = 0
        while (aa != 0 || bb != 0 || cc != 0) {
            val aBit = aa and 1
            val bBit = bb and 1
            val cBit = cc and 1
            if (cBit == 0) {
                if (aBit == 1 && bBit == 1) {
                    count += 2
                } else if (aBit == 1 || bBit == 1) {
                    ++count
                }
            } else {
                if (aBit == 0 && bBit == 0) {
                    ++count
                }
            }
            aa = aa shr 1
            bb = bb shr 1
            cc = cc shr 1
        }
        return count
    }

}