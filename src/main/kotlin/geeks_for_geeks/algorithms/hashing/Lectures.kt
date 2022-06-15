package geeks_for_geeks.algorithms.hashing

import java.util.*
import kotlin.math.max

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
fun countDistinctElementsInArrays(first: IntArray, second: IntArray): Int {
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
 * Consider an array a1, a2, a3 ... ai - 1, ai, ai + 1, ai + 2 ... aj ... an - 1
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

fun subarrayWithGivenSum(array: IntArray, sum: Int): Boolean {
    if (array.isEmpty()) return false
    var prefixSum = 0
    val set = hashSetOf<Int>()
    for (i in array.indices) {
        prefixSum += array[i]
        if (prefixSum == sum || set.contains(prefixSum - sum)) return true
        set.add(prefixSum)
    }
    return false
}

/**
 * Given an array and a sum, find out the length of the longest subarray with the given sum.
 * If there's no subarray with the given sum, return 0.
 */
fun lengthOfLongestSubarrayWithGivenSum(array: IntArray, sum: Int): Int {
    if (array.isEmpty()) return 0
    var maxLength = 0
    var prefixSum = 0
    val prefixSumToEndIdx = hashMapOf<Int, Int>()
    for (i in array.indices) {
        prefixSum += array[i]
        if (prefixSum == sum) {
            maxLength = i + 1
        } else if (prefixSumToEndIdx.containsKey(prefixSum - sum)) {
            maxLength = max(maxLength, i - prefixSumToEndIdx[prefixSum - sum]!!)
        } else {
            // the trick here is NOT TO REPLACE a previous prefix sum's end index,
            // if it's already present in the map, because in this case it means that,
            // current index in the array is the end index of subarray with sum of
            // elements equal to 0, therefore we don't need to update the end index
            // of the previous prefix sum, otherwise we would decrease the length of
            // a possible subarray.
            // ex.        [8,  3,  1,  5,  -6,  6,  2,  2]
            // idx         0   1   2   3    4   5   6   7
            // prefixSum   8  11  12  17   11  17  19  21
            // You can see that we have two prefix sums with 11, one with end index = 1,
            // the other with end index = 4
            // we also have two prefix sums with 17, one with end index = 3,
            // the other with end index = 5, and actually when we try to find the
            // subarray with the given sum, we reach the end of array and check if
            // prefixSum - sum is anywhere in the map. We get: 21 - 4 = 17
            // and 17 is already in the map, BUT if we updated the end index of this
            // prefixSum we would have gotten the length = 7 - 5 = 2,
            // while there's a greater length: 7 - 3 = 4
            prefixSumToEndIdx.putIfAbsent(prefixSum, i)
        }
    }
    return maxLength
}

/**
 * Given a binary array (contains only 1s and 0es), find the length of the longest
 * subarray that contains equal number of zeroes and ones.
 */
fun binaryArraySubarrayLengthNaive(array: IntArray): Int {
    var res = 0
    for (i in array.indices) {
        var zeroes = 0
        var ones = 0
        for (j in i..array.lastIndex) {
            if (array[j] == 1) {
                ++ones
            }
            if (array[j] == 0) {
                ++zeroes
            }
            if (zeroes == ones) {
                res = max(res, zeroes + ones)
            }
        }
    }
    return res
}

fun binaryArraySubarrayLengthEfficient(array: IntArray): Int {
    // this is the trick to modify the array, so that if a subarray contains
    // equal number of ones and zeroes, then the sum of its elements will be
    // equal to zero.
    for (i in array.indices) {
        if (array[i] == 0) {
            array[i] = -1
        }
    }
    // algorithm to find the longest length of subarray with the given sum, here it is = 0
    val sum = 0
    var maxLength = 0
    var prefixSum = 0
    val prefixSumToEndIdx = hashMapOf<Int, Int>()
    for (i in array.indices) {
        prefixSum += array[i]
        if (prefixSum == sum) {
            maxLength = i + 1
        } else if (prefixSumToEndIdx.containsKey(prefixSum - sum)) {
            maxLength = max(maxLength, i - prefixSumToEndIdx[prefixSum - sum]!!)
        } else {
            prefixSumToEndIdx.putIfAbsent(prefixSum, i)
        }
    }
    return maxLength
}







