package yandex_algo_training.year_2023.day_two.string_base

const val x = 31
const val mod = 1_000_000_007

fun main() {
    val s = readln()
    println(solve(s))
}

private fun solve(s: String): Int {
    val n = s.length
    val hash = LongArray(n + 1)
    val pows = LongArray(n + 1)
    pows[0] = 1
    val str = " $s"
    for (i in 1..n) {
        hash[i] = (hash[i - 1] * x + str[i].code) % mod
        pows[i] = (pows[i - 1] * x) % mod
    }
    var curLen = 1
    val prefixStart = 1
    var suffixStart = str.lastIndex
    var maxLength = 0
    while (suffixStart >= 2) {
        if (isEqual(prefixStart, suffixStart, curLen, hash, pows)) {
            maxLength = maxOf(maxLength, curLen)
        }
        ++curLen
        --suffixStart
    }
    return n - maxLength
}

private fun isEqual(aStart: Int, bStart: Int, len: Int, hash: LongArray, pows: LongArray): Boolean {
    return (hash[aStart + len - 1] + hash[bStart - 1] * pows[len]) % mod ==
            (hash[bStart + len - 1] + hash[aStart - 1] * pows[len]) % mod
}