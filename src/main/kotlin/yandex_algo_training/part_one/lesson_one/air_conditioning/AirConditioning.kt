package yandex_algo_training.part_one.lesson_one.air_conditioning

fun main() {
    val (tRoom, tCond) = readln().trim().split(" ").map { it.trim().toInt() }
    val mode = Mode.valueOf(readln().trim().uppercase())
    val result = when(mode) {
        Mode.HEAT -> {
            if (tRoom >= tCond) {
                tRoom
            } else {
                tCond
            }
        }
        Mode.FREEZE -> {
            if (tRoom <= tCond) {
                tRoom
            } else {
                tCond
            }
        }
        Mode.AUTO -> {
            tCond
        }
        Mode.FAN -> {
            tRoom
        }
    }
    println(result)
}

enum class Mode {
    HEAT, FREEZE, AUTO, FAN
}