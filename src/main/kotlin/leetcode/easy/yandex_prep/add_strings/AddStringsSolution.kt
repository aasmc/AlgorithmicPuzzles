package leetcode.easy.yandex_prep.add_strings

class AddStringsSolution {

    fun addStrings(num1: String, num2: String): String {
        var carry = 0
        val minLength = minOf(num1.length, num2.length)
        val sb = StringBuilder()

        for (i in 0 until minLength) {
            val firstDigit = num1[num1.lastIndex - i].digitToInt()
            val secondDigit = num2[num2.lastIndex - i].digitToInt()
            val sum = firstDigit + secondDigit + carry
            if (sum > 9) {
                carry = 1
                sb.append(sum % 10)
            } else {
                carry = 0
                sb.append(sum)
            }
        }
        if (num1.length == num2.length) {
            if (carry != 0) {
                sb.append("1")
            }
        } else if (num1.length > num2.length) {
            addRemaining(num1, minLength, carry, sb)
        } else {
            addRemaining(num2, minLength, carry, sb)
        }

        return sb.toString().reversed()
    }

    private fun addRemaining(num2: String, minLength: Int, carry: Int, sb: StringBuilder) {
        var carry1 = carry
        for (i in num2.lastIndex - minLength downTo 0) {
            val sum = num2[i].digitToInt() + carry1
            if (sum > 9) {
                carry1 = 1
                sb.append(sum % 10)
            } else {
                carry1 = 0
                sb.append(sum)
            }
        }
        if (carry1 != 0) {
            sb.append("1")
        }
    }

}