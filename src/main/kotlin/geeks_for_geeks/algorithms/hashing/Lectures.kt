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

/**
 * Given two arrays that may be unsorted and may contain duplicates, count the number of elements that are
 * distinct in both the arrays combined.
 */
fun countDistinctElementsInArrays(first: IntArray, second: IntArray) : Int {
    val setA = first.toSet()
    var count = setA.size
    val setB = second.toSet()
    setB.forEach { num ->
        if (!setA.contains(num)) {
            ++count
        }
    }
    return count
}

fun countDistinctElementsKotlinStyle(first: IntArray, second: IntArray): Int {
    return first.toSet()
        .union(second.toSet()).size
}


/**
 * Given an unsorted array that may contain duplicates and negative numbers,
 * find if the array contains a pair of elements with the given [sum].
 */
fun findPairWithGivenSum(array: IntArray, sum: Int): Boolean {
    val set = hashSetOf<Int>()
    array.forEach { num ->
        if (set.contains(sum - num)) {
            return true
        } else {
            set.add(num)
        }
    }
    return false
}

/**
 * Given an array, the task is to find out if there's a subarray of contiguous elements
 * with sum of elements equal to 0.
 *
 * The solution is based on using hashing and prefix sum techniques.
 *
 * 1. We start with prefix sum = 0 and an empty set.
 * 2. We iterate through the array and compute the prefix sum by adding the i-th
 *    element to it. If the prefix sum == 0 or the set contains the prefix sum,
 *    then there's a subarray with sum of elements == 0. We stop and return true,
 * 3. Otherwise, we add the prefix sum to the set.
 *
 * What is a prefix sum.
 * Consider array a1, a2, a3 ... ai - 1, ai, ai + 1, ai + 2 ... aj ... an - 1
 * If subarray [ai : aj] has sum = 0, then subarray [a1 : aj] will have sum = [a1 : ai - 1],
 * because adding 0 to the sum won't change the sum. So all we need to do is compute
 * prefix sums.
 */
fun subarrayWithZeroSum(array: IntArray): Boolean {
    if (array.isEmpty()) return false
    var prefixSum = 0
    val set = hashSetOf<Int>()
    for (i in array.indices) {
        prefixSum += array[i]
        if (set.contains(prefixSum) || prefixSum == 0) return true
        set.add(prefixSum)
    }
    return false
}
















