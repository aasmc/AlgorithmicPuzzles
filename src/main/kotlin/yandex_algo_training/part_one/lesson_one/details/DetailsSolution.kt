package yandex_algo_training.part_one.lesson_one.details

fun main() {
    val (n, k, m) = readLine()!!.split(" ").map { it.toInt() }
    if (m > k) {
        println(0)
    } else {
        var currentMetal = n
        var totalDetails = 0
        // while we can produce a blank
        while (currentMetal >= k) {
            // how many blanks we can produce
            val blanks = currentMetal / k
            // how much metal will stay after we produced all the above blanks
            val leftOverFromSpill = currentMetal % k
            // we can make k/m details from every blank
            val detailsFromBlanks = blanks * (k / m)
            val leftOverFromBlanks = blanks * (k % m)
            totalDetails += detailsFromBlanks
            currentMetal = leftOverFromSpill + leftOverFromBlanks
        }
        println(totalDetails)
    }
}