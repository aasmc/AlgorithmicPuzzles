package leetcode.easy.two_pointers.happy_number

class HappyNumberSolution {

    fun isHappy(n: Int): Boolean {
        val seenNumbers = hashSetOf<Int>()
        var start = n
        while (start !in seenNumbers) {
            seenNumbers.add(start)
            var sum = 0
            while (start > 0) {
                val lastDigit = start % 10
                sum += (lastDigit * lastDigit)
                start /= 10
            }
            start = sum
            if (start == 1) {
                return true
            }
        }
        return false
    }

}