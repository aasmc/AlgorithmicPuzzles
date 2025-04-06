package yandex_algo_training.part_one.lesson_one.triangle

fun main() {
    val a = readLine()!!.trim().toInt()
    val b = readLine()!!.trim().toInt()
    val c = readLine()!!.trim().toInt()
    if ((a + b) > c && (b + c) > a && (a + c) > b) {
        println("YES")
    } else {
        println("NO")
    }
}