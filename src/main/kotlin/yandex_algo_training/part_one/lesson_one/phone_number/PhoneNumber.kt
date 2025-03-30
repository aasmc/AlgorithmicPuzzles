package yandex_algo_training.part_one.lesson_one.phone_number

fun main() {
    val toAdd = readPhone()
    val one = readPhone()
    val two = readPhone()
    val three = readPhone()
    check(toAdd, one)
    check(toAdd, two)
    check(toAdd, three)
}

private fun check(toAdd: String, current: String) {
    if (toAdd == current) {
        println("YES")
    } else {
        println("NO")
    }
}

private fun readPhone(): String {
    val dirty = readLine()!!.replace("(", "")
        .replace(")", "")
        .replace("-", "")
    if (dirty.length == 7) {
        return "495$dirty"
    }
    if (dirty.startsWith("+")) {
        return dirty.drop(2)
    }
    return dirty.drop(1)
}