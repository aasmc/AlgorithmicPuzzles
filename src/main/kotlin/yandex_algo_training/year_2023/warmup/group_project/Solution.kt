package yandex_algo_training.year_2023.warmup.group_project

fun main() {
    val tests = readln().toInt()
    repeat(tests) {
        val (n, a, b) = readln().split(" ").map { it.toInt() }
        solve(n, a, b)
    }
}

private fun solve(n: Int, a: Int, b: Int) {
    val minGroups = n / b
    val maxGroups = n / a
    for (curGroups in minGroups ..maxGroups) {
        val curStudents = curGroups * a
        val remainingStudents = n - curStudents
        if (remainingStudents >= 0 && remainingStudents <= (b - a) * curGroups) {
            println("YES")
            return
        }
    }
    println("NO")
}