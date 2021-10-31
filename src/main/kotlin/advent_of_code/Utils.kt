package advent_of_code

import java.io.File

const val BASE_FILE_PATH = "src/main/kotlin/advent_of_code"

fun readInputFromFile(projectDir: String, fileName: String): List<String> {
    return File("$BASE_FILE_PATH/$projectDir/$fileName")
        .readLines()
}