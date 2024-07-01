package leetcode.top_interview_150.stack.simplify_path

class SimplifyPathSolution {

    fun simplifyPath(path: String): String {
        val stack = ArrayDeque<String>()
        for (dir in getDirectories(path)) {
            if (dir == "..") {
                if (stack.isNotEmpty()) {
                    stack.removeLast()
                }
            } else if (dir != ".") {
                stack.addLast(dir)
            }
        }
        val result = StringBuilder()
        result.append("/")
        while (stack.isNotEmpty()) {
            result.append(stack.removeFirst())
            if (stack.isNotEmpty()) {
                result.append("/")
            }
        }
        return result.toString()
    }


    private fun getDirectories(path: String): Sequence<String> = sequence {
        val sb = StringBuilder()
        var i = 0
        while (i < path.length) {
            while (i < path.length && path[i] != '/') {
                sb.append(path[i++])
            }
            val dir = sb.toString()
            if (dir.isNotEmpty()) {
                yield(dir)
            }
            sb.clear()
            while (i < path.length && path[i] == '/') {
                ++i
            }
        }
    }

    fun simplifyPath3(path: String): String {
        val stack = ArrayDeque<String>()

        val sb = StringBuilder()
        for (ch in path) {
            if (ch == '/') {
                if (sb.isNotEmpty()) {
                    val dir = sb.toString()
                    if (dir == "..") {
                        if (stack.isNotEmpty()) {
                            stack.removeLast()
                        }
                    } else if (dir != ".") {
                        stack.addLast(sb.toString())
                    }
                    sb.clear()
                }
            } else {
                sb.append(ch)
            }
        }
        if (sb.isNotEmpty()) {
            val dir = sb.toString()
            if (dir == "..") {
                if (stack.isNotEmpty()) {
                    stack.removeLast()
                }
            } else if (dir != ".") {
                stack.addLast(sb.toString())
            }
        }
        sb.clear()
        sb.append("/")
        while (stack.isNotEmpty()) {
            val current = stack.removeFirst()
            sb.append(current)
            if (stack.isNotEmpty()) {
                sb.append("/")
            }
        }
        return sb.toString()
    }


    fun simplifyPath2(path: String): String {
        val dirs = path.split("/+".toRegex())
        val stack = ArrayDeque<String>()
        for (dir in dirs) {
            if (dir.isNotEmpty()) {
                if (dir == ".") {
                    continue
                }
                if (dir == "..") {
                    if (stack.isNotEmpty()) {
                        stack.removeLast()
                    }
                } else {
                    stack.addLast(dir)
                }
            }
        }
        val result = StringBuilder()
        result.append("/")
        while (stack.isNotEmpty()) {
            result.append(stack.removeFirst())
            if (stack.isNotEmpty()) {
                result.append("/")
            }
        }
        return result.toString()
    }

}