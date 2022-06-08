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

/**
 * Given two unsorted arrays that might contain duplicates, count distinct elements
 * in the intersection of these two arrays.
 */
fun findIntersectionOfArrays(first: IntArray, second: IntArray): Int {
    val map = hashMapOf<Int, Int>()
    first.forEach { num ->
        map.putIfAbsent(num, 1)
    }
    second.forEach { num ->
        map.computeIfPresent(num) { _, value ->
            value + 1
        }
    }
    return map.count { it.value > 1 }
}

fun findIntersectionOfArraysVersionWithSets(first: IntArray, second: IntArray): Int {
    val setA = first.toMutableSet()
    var res = 0
    second.forEach { num ->
        if (num in setA) {
            ++res
            setA.remove(num)
        }
    }
    return res
}