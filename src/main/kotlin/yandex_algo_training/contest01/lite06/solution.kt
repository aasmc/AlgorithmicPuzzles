package yandex_algo_training.contest01.lite06

import java.util.*


fun main() {
    val sectorCount = readLine()!!.toInt()
    val partitionCount = readLine()!!.toInt()
    val partitions = hashSetOf<Partition>()
    if (partitionCount > 0) {
        repeat(partitionCount) {
            val p = Partition.from(readLine()!!, it)
            partitions.add(p)
        }
        val sorted = partitions
            .sortedWith(compareBy<Partition> { it.from }.then(compareBy { it.to }))
        var counter = 1
        var prev = sorted[0]
        for (i in 1 until sorted.size) {
            val current = sorted[i]
            if (!prev.intersects(current)) {
                ++counter
                prev = current
            } else {
                if(prev.idx < current.idx && prev.to < current.from) {
                    prev = current
                }
            }
        }
        println(counter)
    } else {
        println(0)
    }
}

fun Partition.intersects(other: Partition): Boolean {
    if (from in other.from..other.to) return true
    if (to in other.from..other.to) return true
    if (other.from in from..to) return true
    if (other.to in from..to) return true
    return false
}

data class Partition(
    val from: Int,
    val to: Int,
    val idx: Int
) {
    companion object {
        fun from(string: String, idx: Int): Partition {
            val (from, to) = string.split(" ").map { it.toInt() }
            return Partition(from, to, idx)
        }
    }
}


