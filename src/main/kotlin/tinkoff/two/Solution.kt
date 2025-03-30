package tinkoff.two

import java.util.TreeMap

fun main() {
    val (n, k) = readLine()!!.split(" ").map { it.toInt() }
    val p = readLine()!!
    val res: Int = solve(n, k, p)
    println(res)
}

private fun solve(n: Int, k: Int, p: String): Int {
    if (n <= k) return 0
    val charToCount = hashMapOf<Char, Int>()
    p.forEach { ch ->
        charToCount.merge(ch, 1, Int::plus)
    }

    val countToChar = TreeMap<Int, MutableList<Char>>()
    charToCount.forEach { (ch, cnt) ->
        if (countToChar.containsKey(cnt)) {
            countToChar[cnt]!!.add(ch)
        } else {
            countToChar[cnt] = mutableListOf(ch)
        }
    }
    var kk = k
    outer@ for ((cnt, chars) in countToChar) {
        if (kk >= cnt) {
            for (ch in chars) {
                val count = charToCount[ch]!!
                if (count <= kk) {
                    charToCount[ch] = 0
                    kk -= count
                } else {
                    charToCount[ch] = charToCount[ch]!! - kk
                    break@outer
                }
            }
        } else {
            val first = chars.first()
            charToCount[first] = charToCount[first]!! - kk
            break@outer
        }
    }
    var res = 0
    for ((_, cnt) in charToCount) {
        if (cnt > 0) ++res
    }
    return res
}