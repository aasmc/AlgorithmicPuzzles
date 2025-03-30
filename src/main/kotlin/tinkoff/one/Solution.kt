package tinkoff.one

fun main() {
    val s = readLine()!!.toInt()
    val n = readLine()!!.toInt()

    val res: Int = solve(s, n)
    println(res)
}

private fun solve(s: Int, n: Int): Int {
    var ss = s
    var nn = n
    while (nn >= 0 && nn >= ss) {
        nn-= ss
        --ss
        if (ss == 0) {
            ss = s
        }
    }
    return nn
}