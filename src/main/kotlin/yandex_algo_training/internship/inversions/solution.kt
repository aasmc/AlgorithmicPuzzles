package yandex_algo_training.internship.inversions

fun main() {
    val n = readln().toInt()
    val numbers = readln().trim().split("\\s+".toRegex()).map { it.toInt() }.toIntArray()
    val result: Pair<Int, Int> = countAverageInversions(numbers)
    println("${result.first}/${result.second}")
}


private fun countAverageInversions(numbers: IntArray): Pair<Int, Int> {
    var totalInversions = 0
    var totalReplacements = 0
    for (i in numbers.indices) {
        for (j in i + 1 until numbers.size) {
            val copy = IntArray(numbers.size)
            System.arraycopy(numbers, 0, copy, 0, numbers.size)
            val tmp = copy[i]
            copy[i] = copy[j]
            copy[j] = tmp
            totalInversions += countInversions(copy)
            ++totalReplacements
        }
    }
    return reduce(totalInversions, totalReplacements)
}

private fun reduce(left: Int, right: Int): Pair<Int, Int> {
    val gcd = gcd(left, right)
    return left / gcd to right / gcd
}

private tailrec fun gcd(x: Int, y: Int): Int {
    if (y == 0) return x
    return gcd(y, x % y)
}

private fun countInversions(numbers: IntArray): Int {
    var inversions = 0
    for (i in numbers.indices) {
        for (j in i + 1 until numbers.size) {
            if (numbers[i] > numbers[j]) {
                ++inversions
            }
        }
    }
    return inversions
}