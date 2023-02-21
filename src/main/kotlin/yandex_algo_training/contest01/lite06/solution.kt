package yandex_algo_training.contest01.lite06


fun main() {
    val sectorCount = readLine()!!.toInt()
    val partitionCount = readLine()!!.toInt()
    val partitions = arrayListOf<Partition>()
    if (partitionCount > 0) {
        val alive = BooleanArray(partitionCount)
        repeat(partitionCount) {
            val p = Partition.from(readLine()!!)
            partitions.add(p)
            for (i in partitions.indices) {
                val o = partitions[i]
                if(o.intersects(p)) {
                    alive[i] = false
                }
                alive[it] = true
            }
        }
        println(alive.count { it })

    } else {
        println(0)
    }
}

fun Partition.intersects(other: Partition): Boolean {
    if (from <= other.to && other.from <= to) return true
    return false
}

data class Partition(
    val from: Int,
    val to: Int
) {
    companion object {
        fun from(string: String): Partition {
            val (from, to) = string.split(" ").map { it.toInt() }
            return Partition(from, to)
        }
    }
}


