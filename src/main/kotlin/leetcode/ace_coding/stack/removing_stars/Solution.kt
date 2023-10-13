package leetcode.ace_coding.stack.removing_stars


fun removeStars(s: String): String {
    val stack = ArrayDeque<Char>()
    for (ch in s) {
        if (ch == '*') {
            stack.removeLast()
        } else {
            stack.addLast(ch)
        }
    }
    val sb = StringBuilder()
    while (stack.isNotEmpty()) {
        sb.append(stack.removeFirst())
    }
    return sb.toString()
}