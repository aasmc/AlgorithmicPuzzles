package yandex_algo_training.contest01.control04

import kotlin.math.abs

fun main() {
    val numPupils = readLine()!!.toInt()
    val numVariants = readLine()!!.toInt()
    val petyaRow = readLine()!!.toInt()
    val petyaChoice = readLine()!!.toInt()

    val answer: Pair<Int, Int> = findPlaceForVasya(numPupils, numVariants, petyaRow, petyaChoice)

    if (answer.first == -1) {
        println(-1)
    } else {
        println("${answer.first} ${answer.second}")
    }
}

fun findPlaceForVasya(numPupils: Int, numVariants: Int, petyaRow: Int, petyaChoice: Int): Pair<Int, Int> {
    if (numPupils == numVariants) {
        return -1 to -1
    } else {
        val petyaSeat = if (petyaChoice == 1) {
            petyaRow * 2 - 1
        } else {
            petyaRow * 2
        }
        val answer: Pair<Int, Int> = calculateVasyaRowToSeat(
            petyaSeat,
            numVariants,
            petyaRow,
            numPupils
        )
        return answer
    }
}

fun calculateVasyaRowToSeat(
    petyaSeat: Int,
    numVariants: Int,
    petyaRow: Int,
    numStudents: Int
): Pair<Int, Int> {
    var answerSeat = -1
    var answerRow = -1
    val petyaVariant = calculateVariantBySeat(petyaSeat, numVariants)
    var minDistance = Int.MAX_VALUE
    for (k in 1..numStudents) {
        if (k != petyaSeat) {
            val variant = calculateVariantBySeat(k, numVariants)
            if (variant == petyaVariant) {
                val currentRow = (k + 1) / 2
                val currentDistance = abs(petyaRow - currentRow)
                if (currentDistance <= minDistance) {
                    minDistance = currentDistance
                    answerSeat = if (k % 2 == 0) 2 else 1
                    answerRow = (k + 1) / 2
                }
            }
        }
    }
    return answerRow to answerSeat
}

private inline fun calculateVariantBySeat(seat: Int, numVariants: Int): Int {
    return if (seat % numVariants == 0) numVariants else seat % numVariants
}
