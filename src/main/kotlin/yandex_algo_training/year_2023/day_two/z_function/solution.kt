package yandex_algo_training.year_2023.day_two.z_function

const val x = 31
const val mod = 1_000_000_007
fun main() {
    val s = readln()
    val res = solve(s)
    println(res.joinToString(separator = " "))
}

private fun solve(s: String): IntArray {
    val res = IntArray(s.length)
    val n = s.length
    val hash = LongArray(n + 1)
    val pows = LongArray(n + 1)
    pows[0] = 1
    val str = " $s"
    for (i in 1..n) {
        hash[i] = (hash[i - 1] * x + str[i].code) % mod
        pows[i] = (pows[i - 1] * x) % mod
    }

    for (i in 2..n) {
        val r = rightBinSearch(i, n + 1, hash, pows, ::isEqual)
        res[i - 1] = r - i
    }
    return res
}

private fun isEqual(aStart: Int, bStart: Int, len: Int, hash: LongArray, pows: LongArray): Boolean {
    return (hash[aStart + len - 1] + hash[bStart - 1] * pows[len]) % mod ==
            (hash[bStart + len - 1] + hash[aStart - 1] * pows[len]) % mod
}

fun rightBinSearch(
    left: Int,
    right: Int,
    hash: LongArray,
    pows: LongArray,
    check: (Int, Int, Int, LongArray, LongArray) -> Boolean
): Int {
    var l = left
    var r = right
    while (l < r) {
        // round to upper
        val m = l + 1 + (r - l) / 2
        if (check(1, left, m - left, hash, pows)) { // acceptable
            l = m
        } else { // not acceptable
            r = m - 1
        }
    }
    return l
}
