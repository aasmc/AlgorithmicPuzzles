package geeks_for_geeks.algorithms.hashing

/**
 * Counts the number of distinct elements in a given [array].
 */
fun countDistinctElementsIn(array: IntArray): Int {
    val set = hashSetOf<Int>()
    array.forEach { elem ->
        set.add(elem)
    }
    return set.size
}