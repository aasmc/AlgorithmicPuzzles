package leetcode.ace_coding.bit_manipulation.counting_bits

class CountingBitsSolution {

    fun countBits(n: Int): IntArray {
        val res = IntArray(n + 1)
        for (i in 1..n) {
            var count = 0
            var num = i
            while (num > 0) {
                if ((num and 1) == 1) {
                    ++count
                }
                num = num shr 1
            }
            res[i] = count
        }
        return res
    }

}