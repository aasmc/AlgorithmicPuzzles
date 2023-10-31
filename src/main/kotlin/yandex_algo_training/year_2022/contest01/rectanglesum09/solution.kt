package yandex_algo_training.year_2022.contest01.rectanglesum09

import java.nio.file.Files
import java.nio.file.Path
import java.util.stream.Collectors

fun main() {
    val path = Path.of("input.txt")
    val lines = Files.lines(path).collect(Collectors.toList())

    val (rows, cols, requests) = lines[0].split(" ").map { it.toInt() }
    val matrix = Array(rows) {
        IntArray(cols) { 0 }
    }
    var idx = 1
    repeat(rows) { row ->
        val colValues = lines[idx++].split(" ").map { it.toInt() }
        repeat(cols){ col ->
            matrix[row][col] = colValues[col]
        }
    }
    val sums = mutableListOf<Int>()
    repeat(requests) {
        val (x1, y1, x2, y2) = lines[idx++].split(" ").map { it.toInt() - 1 }
        var sum = 0
        for (i in x1..x2) {
            for (j in y1..y2) {
                sum += matrix[i][j]
            }
        }
        sums.add(sum)
    }
    sums.forEach { println(it) }
}