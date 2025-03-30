package yandex_algo_training.internship.inversions

fun main() {
    val n = readln().toInt()
    val numbers = readln().trim().split(" ").map { it.toInt() }.toMutableList()
    val result: Pair<Long, Long> = countInversionAverageViaSequence(numbers)
    println("${result.first}/${result.second}")
}


private fun countInversionAverageViaSequence(numbers: MutableList<Int>): Pair<Long, Long> {
    var totalInv = 0L
    var totalPerm = 0L
    val temp = IntArray(numbers.size)
    permutationSequence(numbers).forEach { permutation ->
        totalInv += countInversionsViaMergeSort(permutation.toMutableList(), temp, permutation.size)
        ++totalPerm
    }
    return reduce(totalInv, totalPerm)
}


private fun permutationSequence(numbers: MutableList<Int>): Sequence<List<Int>> = sequence {
    for (i in numbers.indices) {
        for (j in i + 1 until numbers.size) {
            swap(numbers, i, j)
            yield(numbers)
            swap(numbers, i, j)
        }
    }
}

private fun swap(arr: MutableList<Int>, l: Int, r: Int) {
    val tmp = arr[l]
    arr[l] = arr[r]
    arr[r] = tmp
}

private fun reduce(left: Long, right: Long): Pair<Long, Long> {
    val gcd = gcd(left, right)
    return left / gcd to right / gcd
}

private tailrec fun gcd(x: Long, y: Long): Long {
    if (y == 0L) return x
    return gcd(y, x % y)
}

private fun mergeAndCount(arr: MutableList<Int>, temp: IntArray, left: Int, mid: Int, right: Int): Long {
    var i = left
    var j = mid + 1
    var k = left
    var invCount = 0L
    while (i <= mid && j <= right) {
        if (arr[i] <= arr[j]) {
            temp[k++] = arr[i++]
        } else {
            temp[k++] = arr[j++]
            invCount += (mid - i + 1)
        }
    }
    while (i <= mid) {
        temp[k++] = arr[i++]
    }
    while (j <= right) {
        temp[k++] = arr[j++]
    }
    for(n in left..right) {
        arr[n] = temp[n]
    }
    return invCount
}

private fun mergeSortAndCount(arr: MutableList<Int>, temp: IntArray, l: Int, r: Int): Long {
    var invCount = 0L
    if (l < r) {
        val m = l + (r - l) / 2
        invCount += mergeSortAndCount(arr, temp, l, m)
        invCount += mergeSortAndCount(arr, temp, m + 1, r)
        invCount += mergeAndCount(arr, temp, l, m, r)
    }
    return invCount
}

private fun countInversionsViaMergeSort(arr: MutableList<Int>, temp: IntArray, size: Int): Long {
    return mergeSortAndCount(arr, temp, 0, size - 1)
}
