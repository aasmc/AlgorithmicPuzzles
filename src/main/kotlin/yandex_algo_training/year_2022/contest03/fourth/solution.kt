package yandex_algo_training.year_2022.contest03.fourth

fun main() {
    val heap = ArrayList<Int>()
    val n = readLine()!!.toInt()
    repeat(n) {
        val line = readLine()!!.trim().split(" ")
        if (line.size == 1) {
            println(popHeap(heap))
        } else {
            insertInHeap(line[1].toInt(), heap)
        }
    }
}

private fun popHeap(heap: ArrayList<Int>): Int {
    val answer = heap[0]
    heap[0] = heap[heap.lastIndex]
    var pos = 0
    while (pos * 2 + 2 < heap.size) {
        var maxSonIdx = pos * 2 + 1
        if (heap[pos * 2 + 2] > heap[maxSonIdx]) {
            maxSonIdx = pos * 2 + 2
        }
        if (heap[pos] < heap[maxSonIdx]) {
            swap(pos, maxSonIdx, heap)
            pos = maxSonIdx
        } else {
            break
        }
    }
    heap.removeLast()
    return answer
}

private fun insertInHeap(num: Int, heap: ArrayList<Int>) {
    heap.add(num)
    var pos = heap.lastIndex
    var parentPos = (pos - 1) / 2
    while (pos != 0 && heap[parentPos] < num) {
        swap(parentPos, pos, heap)
        pos = parentPos
        if (pos == 0) break
        parentPos = (pos - 1) / 2
    }
}

private fun swap(from: Int, to: Int, heap: ArrayList<Int>) {
    val tmp = heap[from]
    heap[from] = heap[to]
    heap[to] = tmp
}