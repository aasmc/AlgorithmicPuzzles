package yandex_algo_training.year_2023.day_one.quick_sort

import kotlin.random.Random

fun main() {
    val size = readln().toInt()
    if (size == 0) {
        println()
    } else {
        val numbers = readln().split(" ").map { it.toInt() }.toIntArray()
        quickSort2(numbers, size)
        println(numbers.joinToString(separator = " "))
    }
}

private fun quickSort2(numbers: IntArray, size: Int) {
    fun helper(from: Int, to: Int) {
        if (from < to) {
            val pivotIdx = Random.nextInt(from, to)
            val pivot = numbers[pivotIdx]
            val newPivotIdx = partition(numbers, from, to, pivot)
            helper(from, newPivotIdx)
            helper(newPivotIdx + 1, to)
        }
    }
    helper(0, size)
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
            if (numbers[gt] != numbers[i]) {
                val tmp = numbers[i]
                numbers[i] = numbers[gt]
                numbers[gt] = tmp
            }
            ++gt
        }
    }

    return eq
}

private fun quickSort(numbers: IntArray, size: Int) {
    fun quickSortRec(arr: IntArray, start: Int, end: Int){
        if (start < end) {
            val currentPivot = Random.nextInt(start, end)
            val newPivot = hoarePartition(arr, start, end, currentPivot)
            quickSortRec(arr, start, newPivot)
            quickSortRec(arr, newPivot + 1, end)
        }
    }
    quickSortRec(numbers, 0, size - 1)
}

fun hoarePartition(arr: IntArray, start: Int, end: Int, pivotIdx: Int): Int {
    swap(arr, start, pivotIdx)
    val pivot = arr[start]
    var i = start - 1
    var j = end + 1
    while (true) {
        do {
            ++i
        } while (arr[i] < pivot)
        do {
            --j
        } while (arr[j] > pivot)
        if (j <= i) {
            return j
        }
        swap(arr, i, j)
    }
}

private fun swap(arr: IntArray, from: Int, to: Int) {
    val tmp = arr[from]
    arr[from] = arr[to]
    arr[to] = tmp
}
