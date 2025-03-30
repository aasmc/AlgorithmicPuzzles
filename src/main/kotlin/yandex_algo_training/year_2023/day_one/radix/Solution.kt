package yandex_algo_training.year_2023.day_one.radix

fun main() {
    val n = readln().toInt()
    val strings = ArrayList<String>(n)
    repeat(n) {
        strings.add(readln())
    }
    println("Initial array:")
    println(strings.joinToString(separator = ", "))
    radixSort(strings)
}

private fun radixSort(strings: ArrayList<String>) {

    val m = strings[0].length
    repeat(m) { phase ->
        println("**********")
        println("Phase ${phase + 1}")
        val digitIdx = m - phase - 1
        val buckets = Array<MutableList<String>>(10) {
            mutableListOf()
        }
        countSort(strings, buckets, digitIdx)
        printBuckets(buckets)
    }
    println("**********")
    println("Sorted array:")
    println(strings.joinToString(separator = ", "))
}

private fun countSort(
    strings: ArrayList<String>,
    buckets: Array<MutableList<String>>,
    digitIdx: Int
) {
    strings.forEach { s ->
        val idx = s[digitIdx].digitToInt()
        buckets[idx].add(s)
    }
    var idx = 0
    buckets.forEach { bucket ->
        bucket.forEach { s ->
            strings[idx++] = s
        }
    }
}

private fun printBuckets(buckets: Array<MutableList<String>>) {
    buckets.forEachIndexed { index, strings ->
        print("Bucket $index: ")
        if (strings.isEmpty()) {
            println("empty")
        } else {
            println(strings.joinToString(separator = ", "))
        }
    }
}