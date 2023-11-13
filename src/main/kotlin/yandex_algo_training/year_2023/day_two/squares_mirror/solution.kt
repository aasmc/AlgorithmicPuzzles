package yandex_algo_training.year_2023.day_two.squares_mirror


const val x = 1000033L
const val mod = 1_000_000_007L

fun main() {
    val (n, _) = readln().split(" ").map { it.toInt() }
    val colors = readln().split("\\s+".toRegex())
    val hash = LongArray(n + 1)
    val pows = LongArray(n + 1)
    pows[0] = 1

    for (i in 1..n) {
        hash[i] = (hash[i - 1] * x + colors[i - 1].toInt()) % mod
        pows[i] = (pows[i - 1] * x) % mod
    }
    val realHash = LongArray(n + 1)
    for (i in 1..n) {
        realHash[i] = (realHash[i - 1] * x + colors[n - i].toInt()) % mod
    }

    val possibleCounts = mutableListOf<Int>()
    possibleCounts.add(n)
    var endReal = n - 1
    val mid = n / 2
    for (endMirrored in 1..mid) {
        val startReal = endReal - endMirrored
        if (isEqual(startReal, endReal, realHash, endMirrored, hash, pows, endReal - startReal)) {
            possibleCounts.add(endReal)
        }
        --endReal
    }
    println(possibleCounts.reversed().joinToString(separator = " "))
}

private fun isEqual(
    startReal: Int,
    endReal: Int,
    realHash: LongArray,
    endMirror: Int,
    mirrorHash: LongArray,
    pows: LongArray,
    len: Int,
): Boolean {

    if (endReal == endMirror) {
        return mirrorHash[endMirror] == realHash[endReal]
    }
    // startMirror is always 0
    // to check for equality of two hashes we need to find out if:
    // realHash[endReal] - (realHash[startReal] * pows[len]) % mod ==
    // mirrorHash[endMirror] - (mirrorHash[startMirror] * pows[len]) % mod
    // since startMirror is always 0, the last part of the equation
    // is reduced to mirrorHash[endMirror]
    // mit subtracting can lead to negative numbers, therefore we can
    // reformat the equation, because:
    // a - b == x - y is the same as:
    // a + y == b + x
    // therefore our equation becomes:
    // realHash[endReal] + mirrorHash[startMirror] (which is actually 0) ==
    // (mirrorHash[endMirror] + realHash[startReal] * pows[len]) % mod
    val real = realHash[endReal]
    val mirror = (mirrorHash[endMirror] + (realHash[startReal] * pows[len])) % mod
    return real == mirror
}

