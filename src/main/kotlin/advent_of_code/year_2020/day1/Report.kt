package advent_of_code.year_2020.day1

import advent_of_code.readLinesFromFile

fun main() {
    val numbers = readLinesFromFile("day1", "input.txt")
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