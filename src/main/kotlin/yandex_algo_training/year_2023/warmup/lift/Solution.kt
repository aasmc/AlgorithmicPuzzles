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

private fun solveParts(k: Int, employees: IntArray, n: Int) : Long {
    var res = 0L
    var i = n
    while (i > 0) {
        var currentCapacity= k
        res += i
        currentCapacity -= employees[i]
        employees[i] = 0
        --i
        while (i > 0 && currentCapacity - employees[i] >= 0) {
            currentCapacity -= employees[i]
            employees[i] = 0
            --i
            res++
        }
        res += (i + 1)
        while (i > 0 && employees[i] == 0) {
            --i
        }
    }
    return res
}

private fun solveIntegral(k: Int, employees: IntArray, n: Int): Long {
    var res = 0L
    for (i in n downTo 1) {
        res += (employees[i] / k) * i * 2L
        employees[i] -= (employees[i] / k) * k
    }
    return res
}

private fun wrongSolution(n: Int, k: Int, employees: IntArray): Long {
    var res = 0L
    var i = n
    while (i > 0) {
        var capacity = k
        res += (employees[i] / k) * i * 2L
        capacity -= employees[i] % k
        if (capacity != k) {
            res += i * 2L
            while (capacity > 0 && i > 0) {
                --i
                if (employees[i] <= capacity) {
                    capacity -= employees[i]
                } else {
                    employees[i] -= capacity
                    ++i
                    break
                }
            }
        }
        --i
    }
    return res
}
