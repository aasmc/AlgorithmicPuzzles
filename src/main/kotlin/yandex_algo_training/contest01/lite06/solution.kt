package yandex_algo_training.contest01.lite06


fun main() {
    val sectorCount = readLine()!!.toInt()
    val partitionCount = readLine()!!.toInt()
    val partitions = hashSetOf<Partition>()
    if (partitionCount > 0) {
        repeat(partitionCount) {
            val p = Partition.fromString(readLine()!!)
            partitions.add(p)
        }
        val sorted = partitions
            .sortedWith(compareBy<Partition> { it.from })
        var counter = 1
        var prev = sorted[0]
        for (i in 1 until sorted.size) {
            val current = sorted[i]
            if (!prev.intersects(sorted[i])) {
                ++counter
            }
            prev = current
        }
        println(counter)
    } else {
        println(0)
    }
}

fun Partition.intersects(other: Partition): Boolean {
    if (this.from >= other.from && this.to <= other.to) return true
    if (this.to <= other.to && this.to >= other.from) return true
    if (other.from >= from && other.to <= to) return true
    if (other.to <= to && other.to >= other.from) return true
    return false
}

data class Partition(
    val from: Int,
    val to: Int
) {
    companion object {
        fun fromString(string: String): Partition {
            val (from, to) = string.split(" ").map { it.toInt() }
            return Partition(from, to)
        }
    }
}


