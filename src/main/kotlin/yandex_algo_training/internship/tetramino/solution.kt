package yandex_algo_training.internship.tetramino

fun main() {
    val input = Array<CharArray>(8) {
        CharArray(8)
    }

    repeat(8) {
        val line = readln().toCharArray()
        input[it] = line
    }
    var cnt = 0
    for (row in 0..7) {
        for (col in 0..7) {
            if (input[row][col] != '*') {
                // check top
                if (checkBottomTop(input, row, col, col - 1, col + 1, row - 1)) ++cnt
                // check bottom
                if (checkBottomTop(input, row, col, col - 1, col + 1, row + 1)) ++cnt
                // check left
                if (checkLeftRight(input, row, col, col - 1)) ++cnt
                // checkRight
                if (checkLeftRight(input, row, col, col + 1)) ++cnt
            }
        }
    }
    println(cnt)
}

private fun checkLeftRight(
    input: Array<CharArray>,
    row: Int,
    idx: Int,
    arrow: Int
): Boolean {
    val topCorrect = row - 1 >= 0 && input[row - 1][idx] != '*'
    val bottomCorrect = row + 1 < 8 && input[row + 1][idx] != '*'
    val arrowCorrect = arrow in 0..7 && input[row][arrow] != '*'
    return topCorrect && bottomCorrect && arrowCorrect
}

private fun checkBottomTop(
    input: Array<CharArray>,
    row: Int,
    idx: Int,
    left: Int,
    right: Int,
    arrow: Int
): Boolean {
    val lCorrect = left >= 0 && input[row][left] != '*'
    val rCorrect = right < 8 && input[row][right] != '*'
    val arrowCorrect = arrow in 0..7 && input[arrow][idx] != '*'
    return lCorrect && rCorrect && arrowCorrect
}

