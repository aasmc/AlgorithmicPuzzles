package yandex_algo_training.year_2023.day_two.substring_equality

const val x = 31
const val mod = 1_000_000_007
fun main() {
    var s = readln()
    val n = s.length
    val q = readln().toInt()
    val hash = LongArray(n + 1)
    val pows = LongArray(n + 1)
    pows[0] = 1
    s = " $s"
    for (i in 1..n) {
        hash[i] = (hash[i - 1] * x + s[i].code) % mod
        pows[i] = (pows[i - 1] * x) % mod
    }
    repeat(q) {
        val (l, a, b) = readln().split(" ").map { it.toInt() }
        if (isEqual(a + 1, b + 1, l, hash, pows)) {
            println("yes")
        } else {
            println("no")
        }
    }
}

private fun isEqual(aStart: Int, bStart: Int, len: Int, hash: LongArray, pows: LongArray): Boolean {
    return (hash[aStart + len - 1] + hash[bStart - 1] * pows[len]) % mod ==
            (hash[bStart + len - 1] + hash[aStart - 1] * pows[len]) % mod
}