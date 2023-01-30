package geeks_for_geeks.algorithms.backtracking

/**
 * Given a string and a pattern, find all possible permutations
 * of the string which don't contain pattern.
 */
fun naivePermutations(str: String, pattern: String): List<String> {
    val result = mutableListOf<String>()
    val right = str.length - 1
    fun helper(s: CharArray, left: Int, right: Int) {
        if (left == right) {
            val tmp = s.joinToString(separator = "")
            if (!tmp.contains(pattern)) {
                result.add(s.joinToString(separator = ""))
            }
            return
        } else {
            for (i in left..right) {
                swap(s, i, left)
                helper(s, left + 1, right)
                swap(s, i, left)
            }
        }
    }
    helper(str.toCharArray(), 0, right)
    return result.toList()
}

private fun swap(chars: CharArray, left: Int, right: Int) {
    val tmp = chars[left]
    chars[left] = chars[right]
    chars[right] = tmp
}
