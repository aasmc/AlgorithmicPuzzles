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
        val seatToVariantNum = calculateSeatToVariant(numPupils, numVariants)
        val answer: Pair<Int, Int> = calculateVasyaRowToSeat(
            petyaSeat,
            seatToVariantNum,
            petyaRow
        )
        return answer
    }
}

fun calculateVasyaRowToSeat(
    petyaSeat: Int,
    seatToVariantNum: LinkedHashMap<Int, Int>,
    petyaRow: Int,
): Pair<Int, Int> {
    var answerSeat = -1
    var answerRow = -1
    val petyaVariant = seatToVariantNum[petyaSeat]!!
    var minDistance = Int.MAX_VALUE
    for (k in seatToVariantNum.keys) {
        if (k != petyaSeat) {
            val variant = seatToVariantNum[k]!!
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

fun calculateSeatToVariant(numPupils: Int, numVariants: Int): LinkedHashMap<Int, Int> {
    val result = linkedMapOf<Int, Int>()
    var currentVariant = 1
    for (i in 1..numPupils) {
        result[i] = currentVariant
        currentVariant++
        if (currentVariant > numVariants) {
            currentVariant = 1
        }
    }
    return result
}
