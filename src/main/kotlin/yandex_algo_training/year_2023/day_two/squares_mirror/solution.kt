package yandex_algo_training.year_2023.day_two.squares_mirror


const val x = 31L
const val mod = 1_000_000_007L

fun main() {
    val (n, _) = readln().split(" ").map { it.toInt() }
    val colors = readln().split(" ").map { it.toInt() }
    val hash = LongArray(n + 1)
    val reverseHash = LongArray(n + 1)
    val pows = LongArray(n + 1)
    pows[0] = 1

    for (i in 1..n) {
        hash[i] = (hash[i - 1] * x + colors[i - 1]) % mod
        pows[i] = (pows[i - 1] * x) % mod
    }

    for (i in n downTo 1) {
        reverseHash[n - i] = (reverseHash[n - i + 1] * x + colors[i - 1]) % mod
    }

    val possibleCounts = mutableListOf<Int>()
    for (i in 0 until n) {
        val prefixHash = (hash[i] - hash[0] * pows[i] % mod + mod) % mod
        val suffixHash = (reverseHash[0] - reverseHash[i] * pows[i] % mod + mod) % mod
        if (prefixHash == suffixHash) {
            possibleCounts.add(n - i)
        }
    }

    // Выводим возможные значения K
    if (possibleCounts.isEmpty()) {
        println(n)
    } else {
        println(possibleCounts.joinToString(" "))
    }
}

