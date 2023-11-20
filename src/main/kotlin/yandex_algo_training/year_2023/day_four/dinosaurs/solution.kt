package yandex_algo_training.year_2023.day_four.dinosaurs

fun main() {
    val n = readln().toInt()

    val res: Int = solve(n)
    println(res)
}

private fun solve(n: Int): Int {
    var result = 0
    val cols = hashSetOf<Int>()
    val positive = hashSetOf<Int>()
    val negative = hashSetOf<Int>()
    fun helper(row: Int) {
        if (row == n) {
            ++result
        } else {
            for (col in 0 until n) {
                if (col in cols || (row + col) in positive || (row - col) in negative) {
                    continue
                }
                cols.add(col)
                positive.add(row + col)
                negative.add(row - col)
                helper(row + 1)
                cols.remove(col)
                positive.remove(row + col)
                negative.remove(row - col)
            }
        }
    }
    helper(0)
    return result
}