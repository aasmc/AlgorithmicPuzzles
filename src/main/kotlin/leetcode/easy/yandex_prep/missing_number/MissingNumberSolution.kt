package leetcode.easy.yandex_prep.missing_number

class MissingNumberSolution {

    fun missingNumber(nums: IntArray): Int {
        val counter = IntArray(nums.size + 1)
        nums.forEach { num ->
            counter[num]++
        }
        var result = 0
        for (i in counter.indices) {
            if (counter[i] == 0) {
                result = i
                break
            }
        }
        return result
    }

}