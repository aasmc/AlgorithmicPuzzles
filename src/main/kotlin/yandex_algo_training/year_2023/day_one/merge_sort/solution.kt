package yandex_algo_training.year_2023.day_one.merge_sort

fun main() {
    val size = readln().toInt()
    if (size != 0) {
        val numbers = readln().split(" ").map { it.toInt() }.toIntArray()
        mergeSort(numbers)
        println(numbers.joinToString(separator = " "))
    }
}

private fun mergeSort(numbers: IntArray) {
    mergeRec(numbers, 0, numbers.lastIndex)
}

private fun mergeRec(numbers: IntArray, from: Int, to: Int) {
    if (to > from) {
        val mid = from + (to - from) / 2
        mergeRec(numbers, from, mid)
        mergeRec(numbers, mid + 1, to)
        merge(numbers, from, mid, to)
    }
}

fun merge(arr: IntArray, low: Int, mid: Int, high: Int) {
    val left = IntArray(mid - low + 1) { index ->
        arr[index + low]
    }
    val right = IntArray(high - mid) { index ->
        arr[index + mid + 1]
    }
    var firstIdx = 0
    var secondIdx = 0
    var currentIdx = low
    while (firstIdx < left.size && secondIdx < right.size) {
        if (left[firstIdx] <= right[secondIdx]) {
            arr[currentIdx++] = left[firstIdx++]
        } else {
            arr[currentIdx++] = right[secondIdx++]
        }
    }

    while (firstIdx < left.size) {
        arr[currentIdx++] = left[firstIdx++]
    }

    while (secondIdx < right.size) {
        arr[currentIdx++] = right[secondIdx++]
    }
}