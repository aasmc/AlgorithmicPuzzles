package tinkoff.three

fun main() {
    val n = readLine()!!.trim().toInt()
    val errors = readLine()!!.trim().split(" ").map { it.toInt() }.toIntArray()
    val m = readLine()!!.trim().toInt()
    val periods = ArrayList<Period>(m)
    repeat(m) {
        periods.add(Period.fromString(readLine()!!))
    }
    val res: List<String> = solve(errors, periods)
    res.forEach {
        println(it)
    }
}

private fun solve(errors: IntArray, periods: List<Period>): List<String> {
    val res = ArrayList<String>(periods.size)
    periods.forEach { period ->
        if (isError(errors, period)) {
            res.add("Yes")
        } else {
            res.add("No")
        }
    }
    return res
}

fun isError(errors: IntArray, period: Period): Boolean {
    if (period.from == period.to) return true
    var wasdescending = false
    for (i in period.from until period.to) {
        if (wasdescending && errors[i] < errors[i + 1]) {
            return false
        }
        if (i > period.from && errors[i] < errors[i - 1]) {
            wasdescending = true
        }
        if (wasdescending && errors[period.to] > errors[period.to - 1]) {
            return false
        }
    }
    return true
}

data class Period(
    val from: Int,
    val to: Int
) {
    companion object {
        fun fromString(string: String): Period {
            val (from, to) = string.split(" ").map { it.toInt() }
            return Period(from - 1, to - 1)
        }
    }
}