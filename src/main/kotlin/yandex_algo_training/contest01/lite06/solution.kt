package yandex_algo_training.contest01.lite06


fun main() {
    val sectorCount = readLine()!!.toInt()
    val partitionCount = readLine()!!.toInt()
    val partitions = mutableListOf<Partition>()
    if (partitionCount > 0) {
        repeat(partitionCount) {
            partitions.add(Partition.fromString(readLine()!!))
        }
        partitions.sortWith(compareBy<Partition> { it.range.first }.then(compareBy { it.range.last }))
        var count = 1
        if (partitions.size == 1) {
            count = 1
        } else {
            var prev = partitions[0]
            for (i in 1 until partitions.size) {
                val current = partitions[i]
                if (!intersects(prev, current)) {
                    ++count
                }
                prev = current
            }
        }
        println(count)
    } else {
        println(0)
    }
}

fun intersects(p1: Partition, p2: Partition): Boolean {
    return p1.range.first in p2.range || p1.range.last in p2.range ||
            p2.range.first in p1.range || p2.range.last in p1.range
}

data class Partition(
    val range: IntRange
) {
    companion object {
        fun fromString(string: String): Partition {
            val (from, to) = string.split(" ").map { it.toInt() }
            return Partition(from..to)
        }
    }
}


