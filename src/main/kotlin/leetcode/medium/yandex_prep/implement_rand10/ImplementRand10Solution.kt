package leetcode.medium.yandex_prep.implement_rand10

import kotlin.random.Random

class ImplementRand10Solution {

    fun rand7(): Int {
        return Random.nextInt(8)
    }
    private val matrix = Array(7) {
        IntArray(7)
    }

    private val excludeRow = 6
    private val excludeCol = 5

    init {
        var value = 0
        for (row in 0 until 7) {
            for (col in 0 until 7) {
                matrix[row][col] = value % 10
                ++value
            }
        }
    }
    fun rand10(): Int {
        while (true) {
            val r = rand7() - 1
            val c = rand7() - 1
            if (r == excludeRow || (r == excludeRow - 1 && c >= excludeCol)) {
                continue
            }
            return matrix[r][c] + 1
        }
    }

}