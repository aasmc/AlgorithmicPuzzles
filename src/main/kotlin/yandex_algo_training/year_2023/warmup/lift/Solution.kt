package yandex_algo_training.year_2023.warmup.lift

fun main() {
    val k = readln().toInt()
    val n = readln().toInt()
    val employees = IntArray(n + 1)
    for (i in 1..n) {
        employees[i] = readln().toInt()
    }
    solve(k, employees, n)
}

private fun solve(k: Int, employees: IntArray, n: Int) {
    var res = solveIntegral(k, employees, n)
    res += solveParts(k, employees, n)
    println(res)
}

private fun solveParts(k: Int, employees: IntArray, n: Int) : ULong {
    var res: ULong = 0.toULong()
    var i = n
    while (i > 0 && employees[i] == 0) i--
    while (i > 0) {
        var currentCapacity= k
        res += i.toULong()
        currentCapacity -= employees[i]
        employees[i] = 0
        --i
        while (i > 0 && currentCapacity - employees[i] >= 0) {
            currentCapacity -= employees[i]
            employees[i] = 0
            --i
            res++
        }
        if(currentCapacity != 0) {
            employees[i] -= currentCapacity
        }
        res += (i + 1).toULong()

    }
    return res
}

private fun solveIntegral(k: Int, employees: IntArray, n: Int): ULong {
    var res: ULong = 0.toULong()
    for (i in n downTo 1) {
        if (employees[i] != 0) {
            res += (employees[i] / k).toULong() * i.toULong() * 2.toULong()
            employees[i] -= (employees[i] / k) * k
        }
    }
    return res
}
