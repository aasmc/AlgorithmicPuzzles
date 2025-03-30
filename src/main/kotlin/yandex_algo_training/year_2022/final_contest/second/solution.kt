package yandex_algo_training.year_2022.final_contest.second

import java.util.PriorityQueue

fun main() {
    val (n, w) = readLine()!!.trim().split(" ").map { it.toInt() }
    val tasks = ArrayList<Task>()
    repeat(n) {
        val (start, duration) = readLine()!!.trim().split(" ").map { it.toInt() }
        tasks.add(Task(start, duration))
    }
    tasks.sortBy { it.start }
    val employees = PriorityQueue<Int>()

}

data class Task(
    val start: Int,
    val duration: Int
)