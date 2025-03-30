package yandex_algo_training.year_2023.warmup.middle_level

fun main() {
    val n = readln().toInt()
    val ratings = readln().split(" ").map { it.toInt() }
    solve(ratings)
}

private fun solve(ratings: List<Int>) {
    val prefix = IntArray(ratings.size)
    for (i in 1 until prefix.size) {
        prefix[i] = i * (ratings[i] - ratings[i - 1]) + prefix[i - 1]
    }
    val postfix = IntArray(ratings.size)
    for (i in postfix.lastIndex - 1 downTo 0) {
        val mul = ratings.lastIndex - i
        val post = mul * (ratings[i + 1] - ratings[i])
        postfix[i] = post + postfix[i + 1]
    }
    for (i in prefix.indices) {
        prefix[i] += postfix[i]
    }
    val output = prefix.joinToString(separator = " ")
    println(output)
}