package leetcode.easy.arrays.plus_one

class PlusOneSolution {

    fun plusOne(digits: IntArray): IntArray {
        var incr = 1
        for (i in digits.lastIndex downTo 0) {
            val new = digits[i] + incr
            if (new < 10) {
                incr = 0
                digits[i] = new
                break
            } else {
                digits[i] = 0
                incr = 1
            }
        }
        return if (incr == 0) {
            digits
        } else {
            intArrayOf(incr, *digits)
        }
    }

}