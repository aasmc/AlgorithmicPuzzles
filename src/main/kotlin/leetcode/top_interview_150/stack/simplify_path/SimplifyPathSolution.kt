package leetcode.top_interview_150.stack.simplify_path

class SimplifyPathSolution {

    fun simplifyPath(path: String): String {
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