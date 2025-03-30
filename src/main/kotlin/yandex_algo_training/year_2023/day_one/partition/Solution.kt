package yandex_algo_training.year_2023.day_one.partition

fun main() {
    val size = readln().toInt()
    if (size == 0) {
        readln()
        readln()
        println(0)
        println(0)
    } else {
        val numbers = readln().split(" ").map { it.toInt() }.toIntArray()
        val pivot = readln().toInt()
        val idx = partition(numbers, 0, size, pivot)
        println(idx)
        println(size - idx)
    }

}

private fun partition(numbers: IntArray,
                      start: Int,
                      end: Int,
                      pivot: Int): Int {
    var now = start
    var gt = start
    var eq = start

    for (i in start until end) {
        // case 1: x < pivot
        if (numbers[i] < pivot) {
            val tmp = numbers[i]
            numbers[i] = numbers[gt]
            numbers[gt] = numbers[eq]
            ++gt
            numbers[eq] = tmp
            ++eq
            // case 2: x > pivot
        } else if (numbers[i] > pivot) {
            ++now
        } else if (numbers[i] == pivot) {
            val tmp = numbers[i]
            numbers[i] = numbers[gt]
            numbers[gt] = tmp
            ++gt
        }
    }

    return eq
}