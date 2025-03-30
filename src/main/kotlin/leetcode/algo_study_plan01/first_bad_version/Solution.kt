package leetcode.algo_study_plan01.first_bad_version

fun firstBadVersion(n: Int): Int {
    var start = 1
    var end = n
    while (start <= end) {
        val middle = start + (end - start) / 2
        if (isBadVersion(middle)) {
            if (isBadVersion(middle - 1)) {
                end = middle - 1
            } else {
                return middle
            }
        } else {
            start = middle + 1
        }
    }
    return -1
}

/**
 * Function used for testing purposes. Actual implementation is on LeetCode.
 */
private fun isBadVersion(version: Int): Boolean {
    return when (version) {
        4 -> true
        5 -> true
        else -> false
    }
}