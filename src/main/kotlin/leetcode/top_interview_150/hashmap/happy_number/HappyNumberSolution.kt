package leetcode.top_interview_150.hashmap.happy_number

class HappyNumberSolution {

    fun isHappy(n: Int): Boolean {
        val calculated = hashSetOf<Int>()
        var start = n
        while (start !in calculated) {
            calculated.add(start)
            start = squareOfDigits(start)
            if (start == 1) {
                return true
            }
        }
        return false
    }

    private fun squareOfDigits(start: Int): Int {
        var sum = 0
        var number = start
        while (number > 0) {
            val lastDigit = number % 10
            sum += (lastDigit * lastDigit)
            number /= 10
        }
        return sum
    }


}