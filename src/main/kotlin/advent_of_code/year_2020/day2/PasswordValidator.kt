package advent_of_code.year_2020.day2

import advent_of_code.readLinesFromFile

fun main() {
    val passwords = readLinesFromFile("day2", "input.txt")
        .map(PasswordWithPolicy::parse)

    println(passwords.count { it.validatePartOne() })
    println(passwords.count { it.validatePartTwo() })
}

data class PasswordWithPolicy(
    val password: String,
    val range: IntRange,
    val letter: Char
) {

    /**
     * Checks if the number of occurrences of the letter is within a given range.
     */
    fun validatePartOne() = password.count {
        it == letter
    } in range

    /**
     * Checks if the letter occurs only once at position range.first or at
     * position range.last. Here we use XOR for booleans, because XOR returns true only if
     * one of the two arguments are the same. We check that either the symbol in the password at index
     * range.first - 1 (need to subtract 1, because indexing starts at 0) is equal to letter
     * or the symbol at index range.last - 1 is equal to letter.
     */
    fun validatePartTwo() =
        (password[range.first - 1] == letter) xor
                (password[range.last - 1] == letter)

    companion object {

        private val regex = Regex("""(\d+)-(\d+) ([a-z]): ([a-z]+)""")

        fun parse(line: String): PasswordWithPolicy = PasswordWithPolicy(
            password = line.substringAfter(": "),
            letter = line.substringAfter(" ").substringBefore(":").single(),
            range = line.substringBefore(" ").let {
                val (start, end) = it.split("-")
                start.toInt()..end.toInt()
            }
        )

        fun parseWithRegex(line: String): PasswordWithPolicy? {
            return regex.matchEntire(line)
                ?.destructured
                ?.let { (start, end, letter, password) ->
                    PasswordWithPolicy(password, start.toInt()..end.toInt(), letter.single())
                }
        }
    }
}