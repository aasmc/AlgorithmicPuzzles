package yandex_algo_training.year_2022.contest03.second

fun main() {
    val firstDeck = readLine()!!.trim().split(" ").map { it.toInt() }
    val secondDeck = readLine()!!.trim().split(" ").map { it.toInt() }

    val firstQueue = ArrayDeque<Int>(firstDeck)
    val secondQueue = ArrayDeque<Int>(secondDeck)
    var counter = 0
    var win = false
    while (!win) {
        val first = firstQueue.removeFirst()
        val second = secondQueue.removeFirst()
        if (first == 0 && second == 9) {
            firstQueue.addLast(first)
            firstQueue.addLast(second)
        } else if (second == 0 && first == 9) {
            secondQueue.addLast(first)
            secondQueue.addLast(second)
        } else if (first > second) {
            firstQueue.addLast(first)
            firstQueue.addLast(second)
        } else {
            secondQueue.addLast(first)
            secondQueue.addLast(second)
        }
        if (firstQueue.isEmpty() || secondQueue.isEmpty()) {
            win = true
        }
        ++counter
        if (counter == 1_000_000) break
    }
    if (counter == 1_000_000) {
        println("botva")
    } else {
        if (firstQueue.isEmpty()) {
            println("second $counter")
        } else {
            println("first $counter")
        }
    }
}