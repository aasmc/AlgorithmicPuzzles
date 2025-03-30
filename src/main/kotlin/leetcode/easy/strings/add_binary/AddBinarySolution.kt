package leetcode.easy.strings.add_binary

import java.math.BigInteger

class AddBinarySolution {

    fun addBinary(a: String, b: String): String {
        val sb = StringBuilder()
        var carry = 0
        val maxLen = maxOf(a.length, b.length)
        for (i in 0 until maxLen) {
            // take last digits from the strings, and if the string has no more digits,
            // then take 0 instead
            val idxA = a.lastIndex - i
            val idxB = b.lastIndex - i
            val digitA = if (idxA >= 0) a[idxA].digitToInt() else 0
            val digitB = if (idxB >= 0) b[idxB].digitToInt() else 0
            val total = digitA + digitB + carry
            // when doing binary sum we may have the following options
            // (we don't consider all permutations, only the digits that take part in the
            // operation). Therefore we can take modulo of total to get the
            // actual character that we need to place in that position. We also
            // divide total by 2 to get the carry
            // a + b + carry                                           carry = total / 2
            // 1 + 1 + 1 -> 1 and carry = 1; total = 3; total % 2 = 1; carry = 3 / 2 = 1
            // 0 + 0 + 0 -> 0 and carry = 0; total = 0; total % 2 = 0; carry = 0 / 2 = 0
            // 0 + 1 + 1 -> 0 and carry = 1; total = 2; total % 2 = 0; carry = 2 / 2 = 1
            // 1 + 0 + 0 -> 1 and carry = 0; total = 1; total % 2 = 1; carry = 1 / 2 = 0
            val char = total % 2
            sb.append(char)
            carry = total / 2
        }
        if (carry != 0) {
            sb.append(1)
        }
        return sb.toString().reversed()
    }

    fun addBinary2(a: String, b: String): String {
        val aNum = BigInteger(a, 2)
        val bNum = BigInteger(b, 2)
        val sum = aNum.plus(bNum)
        return sum.toString(2)
    }

}