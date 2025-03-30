package leetcode.dp.fibonacci

fun fib(n: Int): Int {
    if (n == 0) return 0
    var curr = 0
    var next = 1
    for (i in 2 .. n) {
        val tmp = curr + next
        curr = next
        next = tmp
    }
    return next
}