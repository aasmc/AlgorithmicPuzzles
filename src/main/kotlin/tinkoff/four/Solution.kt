package tinkoff.four

fun main() {
    val (n, c, d) = readLine()!!.trim().split(" ").map { it.toInt() }
    val checks = readLine()!!.trim().split(" ").map { it.toInt() }.toIntArray()
    val result: IntArray = solve(checks, c, d, n)
    for (i in result.indices) {
        print(i)
        if (i < result.lastIndex) {
            print(" ")
        }
    }
}

private fun solve(checks: IntArray, c: Int, d: Int, n: Int): IntArray {
    return intArrayOf()
}

private fun getTotalDanger(checks: IntArray): Long {
    var result = 1L
    checks.forEach {
        result *= it
    }
    return result
}
