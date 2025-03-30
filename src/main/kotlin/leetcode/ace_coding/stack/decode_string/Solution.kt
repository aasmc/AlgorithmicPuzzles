package leetcode.ace_coding.stack.decode_string

fun decodeString(s: String): String {
    val stack = ArrayDeque<Char>()
    for (ch in s) {
        if (ch != ']') {
            stack.addLast(ch)
        } else {
            val sb = StringBuilder()
            while (stack.last() != '[') {
                sb.append(stack.removeLast())
            }
            val str = sb.toString().reversed()
            sb.clear()
            stack.removeLast() // remove [
            while (stack.isNotEmpty() && stack.last().isDigit()) {
                sb.append(stack.removeLast())
            }
            val digit = sb.toString().reversed().toInt()
            sb.clear()
            repeat(digit) {
                sb.append(str)
            }
            for (c in sb.toString()) {
                stack.addLast(c)
            }
        }
    }
    return stack.joinToString(separator = "")
}