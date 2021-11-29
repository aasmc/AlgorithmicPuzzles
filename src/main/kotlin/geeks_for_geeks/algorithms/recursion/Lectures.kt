package geeks_for_geeks.algorithms.recursion

fun logBaseTwoFloorRecursive(num: Int): Int {
    if (num == 1) {
        return 0
    }
    return 1 + logBaseTwoFloorRecursive(num / 2)
}

/**
 * Returns the sum of [ceil] natural numbers using
 * recursion.
 */
fun sumOfNRecursive(ceil: Int): Int {
    if (ceil == 0) {
        return 0
    }
    return ceil + sumOfNRecursive(ceil - 1)
}