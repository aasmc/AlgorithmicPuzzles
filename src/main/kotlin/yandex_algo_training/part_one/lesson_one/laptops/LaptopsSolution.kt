package yandex_algo_training.part_one.lesson_one.laptops

fun main() {
    val (a1, b1, a2, b2) = readLine()!!.trim().split(" ").map { it.toInt() }

    val orientations1 = listOf(a1 to b1, b1 to a1)
    val orientations2 = listOf(a2 to b2, b2 to a2)
    var bestW = 0
    var bestH = 0
    var bestArea = Int.MAX_VALUE
    for((w1, h1) in orientations1) {
        for ((w2, h2) in orientations2) {
            // try horizontal arrangement
            val width1 = w1 + w2
            val height1 = maxOf(h1, h2)
            val area1 = width1 * height1
            if (area1 < bestArea) {
                bestArea = area1
                bestW = width1
                bestH = height1
            }
            // try vertical arrangement
            val width2 = maxOf(w1, w2)
            val height2 = h1 + h2
            val area2 = width2 * height2
            if (area2 < bestArea) {
                bestArea = area2
                bestW = width2
                bestH = height2
            }
        }
    }
    println("$bestW $bestH")
}