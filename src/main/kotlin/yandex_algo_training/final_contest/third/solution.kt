package yandex_algo_training.final_contest.third

import java.util.ArrayList

fun main() {
    val n = readLine()!!.trim().toInt()
    val orders = ArrayList<Order>(n)
    repeat(n) {
        orders.add(Order.fromString(readLine()!!, it))
    }
    if (n == 1) {
        println(1)
    } else if (n == 2) {
        println("1 2")
    } else {
        orders.sortBy { it.delivery + it.returnTime }


    }
}

data class Order(
    val delivery: Int,
    val returnTime: Int,
    val idx: Int
) {
    companion object {
        fun fromString(str: String, idx: Int): Order {
            val (d, r) = str.split(" ").map { it.trim().toInt() }
            return Order(d, r, idx)
        }
    }
}