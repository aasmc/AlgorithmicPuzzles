package yandex_algo_training.year_2023.warmup.add_fractions

fun main() {
    val (a, b, c, d) = readln().split(" ").map { it.toInt() }
    solve(a, b, c, d)
}

private fun solve(a: Int, b: Int, c: Int, d: Int) {
    val numerator = a * d + c * b
    val denominator = b * d
    val gcd = if (numerator >= denominator) {
        gcd(numerator, denominator)
    } else {
        gcd(denominator, numerator)
    }
    println("${numerator/gcd} ${denominator/gcd}")
}

fun gcd(a: Int, b: Int): Int {
    if (b == 0) {
        return a
    }
    return gcd(b, a % b)
}