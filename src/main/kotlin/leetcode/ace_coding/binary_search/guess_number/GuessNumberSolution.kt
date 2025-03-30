package leetcode.ace_coding.binary_search.guess_number

class GuessNumberSolution {

    fun guessNumber(n: Int): Int {
        var low = 0
        var high = n - 1
        while (low <= high) {
            val mid = low + (high - low) / 2
            val guess = guess(mid)
            if (guess == 0) {
                return mid
            } else if (guess == -1) {
                high = mid - 1
            } else {
                low = mid + 1
            }
        }
        return -1
    }

    private fun guess(num: Int): Int {
        // stub
        return -1
    }

}