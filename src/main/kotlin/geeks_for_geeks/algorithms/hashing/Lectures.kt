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

fun findFrequenciesOfElements(array: IntArray): Map<Int, Int> {
    val res = hashMapOf<Int, Int>()
    array.forEach { num ->
        res.merge(num, 1, Int::plus)
    }
    return res
}