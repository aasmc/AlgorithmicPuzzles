package advent_of_code.year_2020.day4

import advent_of_code.readTextFromFile

fun main() {
    val passwords = readTextFromFile("day4")
        .trim()
        .split("\n\n", "\r\n\r\n")
        .map { Passport.fromString(it) }

    println(passwords.count { it.hasAllRequiredFields() && it.hasAllFieldsValid() })
}
