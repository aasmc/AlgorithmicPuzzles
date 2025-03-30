package advent_of_code

import java.io.File

const val BASE_FILE_PATH = "src/main/kotlin/advent_of_code"

fun readLinesFromFile(projectDir: String, fileName: String = "input.txt"): List<String> {
    return File("$BASE_FILE_PATH/$projectDir/$fileName")
        .readLines()
}

fun readTextFromFile(projectDir: String, fileName: String = "input.txt"): String {
    return File("$BASE_FILE_PATH/$projectDir/$fileName")
        .readText()
}