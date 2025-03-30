package yandex_algo_training.year_2022.contest02.layland15

fun main() {
    val numCities = readLine()!!.toInt()
    val costs = readLine()!!.split(" ").map { it.toInt() }
    val afterMigration: List<Int> = processCosts(costs)
    println(afterMigration.joinToString(separator = " "))
}

fun processCosts(costs: List<Int>): List<Int> {
    val stack = ArrayDeque<Cost>()
    val result = MutableList(costs.size) { -1 }
    for (i in costs.indices) {
        val current = costs[i]
        while (stack.isNotEmpty() && stack.last().cost > current) {
            val last = stack.removeLast()
            result[last.idx] = i
        }
        stack.addLast(Cost(current, i))
    }
    return result
}

data class Cost(
    val cost: Int,
    val idx: Int
)