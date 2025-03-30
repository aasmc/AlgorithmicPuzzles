package yandex_algo_training.year_2022.final_contest.fifth

fun main() {
    val (h, w) = readLine()!!.trim().split(" ").map { it.toInt() }
    val matrix = Array<CharArray>(h) {
        CharArray(w)
    }
    repeat(h) {
        matrix[it] = readLine()!!.trim().toCharArray()
    }

    val (sCol, sRow) = readLine()!!.trim().split(" ").map { it.toInt() }
    val (eCol, eRow) = readLine()!!.trim().split(" ").map { it.toInt() }

}