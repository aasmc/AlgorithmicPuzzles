package yandex_algo_training.year_2022.contest01.lite06

data class Event(
    val time: Int,
    val type: Int
)

fun main() {
    val sectorCount = readLine()!!.toInt()
    val partitionCount = readLine()!!.toInt()

    if (partitionCount > 0) {
        val events = mutableListOf<Event>()
        repeat(partitionCount) {
            val (from, to) = readLine()!!.split(" ").map { it.toInt() }
            events.add(Event(from, -1))
            events.add(Event(to, 1))
        }
        events.sortWith(compareBy<Event> { it.time }.then(compareBy { it.type }))
        var counter = 0
        var current = 0
        for (event in events) {
            if (current == 0) {
                ++counter
            }
            if (event.type == -1) {
                ++current
            } else {
                --current
            }
        }
        println(counter)
    } else {
        println(0)
    }
}