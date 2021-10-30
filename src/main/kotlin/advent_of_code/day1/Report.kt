package advent_of_code.day1

import advent_of_code.Utils.BASE_FILE_PATH
import java.io.File

fun main() {
    val numbers = File("$BASE_FILE_PATH/day1/input.txt")
        .readLines()
        .map(String::toInt)

    for (first in numbers) {
        for (second in numbers) {
            for (third in numbers) {
                if (first + second + third == 2020) {
                    println("$first, $second, $third")
                    println(first * second * third)
                    return
                }
            }
        }
    }
}