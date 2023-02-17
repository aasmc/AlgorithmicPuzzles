package yandex_algo_training.contest03.fifth

fun main() {
    val n = readLine()!!.trim().toInt()
    val array = IntArray(n)
    val nums = readLine()!!.split(" ")
    repeat(n) { idx ->
        array[idx] = nums[idx].toInt()
    }
    heapSort(array)
    println(array.joinToString(separator = " "))
}

private fun heapSort(array: IntArray) {
    val size = array.size
    for (i in size / 2 - 1 downTo 0) {
        heapify(array, size, i)
    }
    for (i in size - 1 downTo 1) {
        val tmp = array[0]
        array[0] = array[i]
        array[i] = tmp

        heapify(array, i, 0)
    }
}

private fun heapify(array: IntArray, size: Int, idx: Int) {
    var largest = idx
    val l = 2 * idx + 1
    val r = 2 * idx + 2
    if (l < size && array[l] > array[largest]) {
        largest = l
    }
    if (r < size && array[r] > array[largest]) {
        largest = r
    }
    if (largest != idx) {
        val swap = array[idx]
        array[idx] = array[largest]
        array[largest] = swap
        heapify(array, size, largest)
    }
}

