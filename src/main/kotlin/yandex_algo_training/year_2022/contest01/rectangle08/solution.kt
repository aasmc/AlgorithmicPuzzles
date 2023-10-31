package yandex_algo_training.year_2022.contest01.rectangle08

fun main() {
    val k = readLine()!!.toInt()
    val coords = mutableListOf<Pair<Int, Int>>()
    repeat(k) {
        val (x, y) = readLine()!!.split(" ").map { it.toInt() }
        coords.add(x to y)
    }
    val minX = coords.minOf { it.first }
    val maxX = coords.maxOf { it.first }
    val minY = coords.minOf { it.second }
    val maxY = coords.maxOf { it.second }

    println("$minX $minY $maxX $maxY")
}