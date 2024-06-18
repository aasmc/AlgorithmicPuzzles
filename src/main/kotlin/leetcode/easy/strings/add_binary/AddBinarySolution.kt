package leetcode.easy.strings.add_binary

import java.math.BigInteger

class AddBinarySolution {

    fun addBinary(a: String, b: String): String {
        val aNum = BigInteger(a, 2)
        val bNum = BigInteger(b, 2)
        val sum = aNum.plus(bNum)
        return sum.toString(2)
    }

}