package yandex_algo_training.contest02.litesorting14

fun main() {
    val numCarriages = readLine()!!.toInt()
    val carriages = readLine()!!.split(" ")
        .map { it.toInt() }

    val canGo: String = processCarriages(carriages)
    println(canGo)
}

/**
 * Can push onto the stack only if the stack is empty or current element is less than
 * the top of the stack.
 *
 * Can pop from the stack only if top of the stack is greater than the previously popped element by 1.
 */
fun processCarriages(carriages: List<Int>): String {
    val stack = ArrayDeque<Int>()
    var checkRes = 0
    for (i in carriages.indices) {
        if (stack.isNotEmpty()) {
            var top = stack.last()
            while (top == checkRes + 1) {
                ++checkRes
                stack.removeLast()
                if (stack.isEmpty()) {
                    break
                }
                top = stack.last()
            }

            if (stack.isEmpty()) {
                stack.addLast(carriages[i])
            } else {
                top = stack.last()
                if (carriages[i] < top) {
                    stack.addLast(carriages[i])
                } else {
                    return "NO"
                }
            }

        } else {
            stack.addLast(carriages[i])
        }
    }
    return "YES"
}
