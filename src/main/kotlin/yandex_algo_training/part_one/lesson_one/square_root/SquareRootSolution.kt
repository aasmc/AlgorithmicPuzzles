package yandex_algo_training.part_one.lesson_one.square_root

fun main() {
    val a = readLine()!!.trim().toInt()
    val b = readLine()!!.trim().toInt()
    val c = readLine()!!.trim().toInt()

    if (a != 0) {
        if (c < 0) {
            println("NO SOLUTION")
        } else {
            val numerator = c * c - b
            if (numerator % a == 0) {
                println(numerator / a)
            } else {
                println("NO SOLUTION")
            }
        }
    } else {
        if (c < 0 || b < 0) {
            println("NO SOLUTION")
        } else if (c * c == b) {
            println("MANY SOLUTIONS")
        } else {
            println("NO SOLUTION")
        }
    }
}