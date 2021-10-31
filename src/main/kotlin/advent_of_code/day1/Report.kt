package advent_of_code.day1

import advent_of_code.readInputFromFile

fun main() {
    val numbers = readInputFromFile("day1", "input.txt")
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