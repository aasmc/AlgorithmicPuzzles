package yandex_algo_training.year_2023.day_four.all_permutations

import kotlin.math.pow

fun main() {
    val n = readln().toInt()
    val permutations: List<Int> = solve(n)
    permutations.forEach {
        println(it)
    }
}

fun solve(n: Int): List<Int> {
    val result = mutableListOf<Int>()
    val visited = BooleanArray(n)
    fun helper(current: Int, res: Int, len: Int) {
        visited[current - 1] = true
        val p = 10.0.pow(len).toInt()
        val curRes = current * p + res
        if (len == 0) {
            result.add(curRes)
        } else {
            for (j in 1..n) {
                if (!visited[j - 1]) {
                    helper(j, curRes, len - 1)
                }
            }
        }
        visited[current - 1] = false
    }
    for (i in 1..n) {
        helper(i, 0, n - 1)
    }
    return result
}
