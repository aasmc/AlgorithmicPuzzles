package geeks_for_geeks.algorithms.arrays

import com.sun.source.tree.ReturnTree
import kotlin.math.abs
import kotlin.math.max
import kotlin.math.min

/**
 * Inserts an integer [elem] into array [arr] at specified index.
 * If the array is full, then no insertion happens.
 *
 * Here we distinguish between the size of the array and capacity of the array.
 *
 * Returns size of the array.
 */
fun insertIntoArray(
    arr: ArrayList<Int>,
    elem: Int,
    index: Int,
    size: Int,
    capacity: Int
): Int {
    if (size == capacity) {
        return size
    }
    for (i in size - 1 downTo index) {
        arr.add(i + 1, arr[i])
    }
    arr[index] = elem
    return size + 1
}

/**
 * Deletes an element from integer array [arr], if the element [elem]
 * is present in the array. Shifts all elements that are stored in the array
 * after the [elem] to one position towards the beginning of the array.
 *
 *
 * @return new size of the array.
 */
fun deleteFromArray(arr: ArrayList<Int>, elem: Int, size: Int): Int {
    var index = -1
    for (i in arr.indices) {
        if (arr[i] == elem) {
            index = i
        }
    }
    if (index == -1) {
        return size
    }
    for (i in index until size - 1) {
        arr[i] = arr[i + 1]
    }
    return size - 1
}

/**
 * Returns index of the second largest element in a given array.
 * If the array contains only one element, its index is returned.
 *
 * @return index of the second largest element in the array
 *         or -1 if all elements are the same
 * @throws IllegalArgumentException if the array is empty.
 */
fun secondLargestElementInArray(arr: IntArray): Int {
    if (arr.isEmpty()) {
        throw IllegalArgumentException("Array $arr is empty")
    }
    if (arr.size == 1) {
        return 0
    }
    var max = 0
    var secondMax = 0

    for (i in 1 until arr.size) {
        if (arr[i] > arr[max]) {
            secondMax = max
            max = i
        } else if (arr[i] > arr[secondMax]) {
            secondMax = i
        }
    }
    if (max == secondMax) {
        return -1
    }
    return secondMax
}

/**
 * Checks if a given array is sorted in ascending order.
 * Time complexity O(N).
 *
 * @return true if the array [arr] is sorted in ascending order
 *         false otherwise.
 * @throws IllegalArgumentException if the array is empty.
 */
fun checkIfSortedArray(arr: IntArray): Boolean {
    if (arr.isEmpty()) {
        throw IllegalArgumentException("Array $arr is empty.")
    }
    var checkIndex = 0
    for (i in 1 until arr.size) {
        if (arr[checkIndex] > arr[i]) {
            return false
        }
        checkIndex = i
    }
    return true
}

fun reverseArray(arr: IntArray) {
    var left = 0
    var right = arr.lastIndex
    while (left < right) {
        swap(arr, left++, right--)
    }
}

private fun swap(arr: IntArray, left: Int, right: Int) {
    val tmp = arr[left]
    arr[left] = arr[right]
    arr[right] = tmp
}

/**
 * Removes duplicate elements from a sorted array.
 * It doesn't deallocate elements or create a new array without
 * duplicates. It simply returns new size of the array, where all
 * the elements from 0 to size are sorted and contain no duplicates.
 *
 * @return number of distinct elements .
 */
fun removeDuplicatesFromArray(arr: IntArray): Int {
    if (arr.size == 1) {
        return 1
    }
    var positionAfterUnique = 1
    for (i in 1 until arr.size) {
        if (arr[i] != arr[positionAfterUnique - 1]) {
            arr[positionAfterUnique] = arr[i]
            ++positionAfterUnique
        }
    }
    return positionAfterUnique
}

/**
 * Moves all zeroes in the array, if any, to the end of the array,]
 * the rest of the elements appear in the array in the initial order.
 */
fun moveZeroesToTheEndOfArray(arr: IntArray) {
    var zeroIndex = 0
    for (i in arr.indices) {
        if (arr[i] != 0) {
            swap(arr, zeroIndex, i)
            ++zeroIndex
        }
    }
}

/**
 * Rotates array by one position counterclockwise in place.
 */
fun leftRotateArrayByOne(arr: IntArray) {
    if (arr.isEmpty() || arr.size == 1) {
        return
    }
    val tmp = arr[0]
    for (i in 1 until arr.size) {
        arr[i - 1] = arr[i]
    }
    arr[arr.lastIndex] = tmp
}


/**
 * Rotates array by D positions counterclockwise in place.
 *
 * Time complexity  O(N*D)
 */
fun leftRotateByD(arr: IntArray, d: Int) {
    repeat(d) {
        leftRotateArrayByOne(arr)
    }
}

fun leftRotateUsingTmpArray(arr: IntArray, d: Int) {
    if (arr.isEmpty() || arr.size == d || arr.size == 1) {
        return
    }
    val dd = d % arr.size
    val tmpArr = IntArray(dd)
    for (i in 0 until dd) {
        tmpArr[i] = arr[i]
    }
    for (i in dd until arr.size) {
        arr[i - dd] = arr[i]
    }
    for (i in 0 until dd) {
        arr[arr.size - dd + i] = tmpArr[i]
    }
}

fun leftRotateReverse(arr: IntArray, d: Int) {
    if (arr.size == d) {
        return
    }
    val numRotations = d % arr.size
    reverseArray(arr, 0, numRotations - 1)
    reverseArray(arr, numRotations, arr.lastIndex)

    reverseArray(arr, 0, arr.lastIndex)
}

private fun reverseArray(array: IntArray, start: Int, end: Int) {
    var e = end
    for (i in start..(start + end) / 2) {
        val tmp = array[i]
        array[i] = array[e]
        array[e] = tmp
        --e
    }
}

/**
 * Given array of integers find leaders in that array.
 * An element is considered to be a leader if no other element
 * in the right part of the array is greater than that element.
 *
 * E.g. [7,10,4,3,6,5,2]
 * Leaders: [10,6,5,2]
 *
 * Time complexity O(N^2)
 */
fun findLeadersInArrayNaive(arr: IntArray): List<Int> {
    val res = mutableListOf<Int>()
    for (i in arr.indices) {
        var leader = true
        for (j in i + 1 until arr.size) {
            if (arr[i] <= arr[j]) {
                leader = false
                break
            }
        }
        if (leader) {
            res.add(arr[i])
        }
    }
    return res
}

fun findLeadersInArrayBackwards(arr: IntArray): List<Int> {
    val res = mutableListOf<Int>()
    var currentLeader = arr[arr.lastIndex]
    res.add(currentLeader)
    for (i in arr.lastIndex - 1 downTo 0) {
        if (currentLeader < arr[i]) {
            currentLeader = arr[i]
            res.add(currentLeader)
        }
    }
    return res
}

/**
 * Given an array of integers, find maximum value
 * arr[j] - arr[i] such that j > i.
 *
 * @return max difference of elements in the array.
 *         If the array is empty it throws IllegalArgumentException.
 *         If the array's size is 1, return its only element.
 */
fun findMaxDifference(arr: IntArray): Int {
    if (arr.isEmpty()) {
        throw IllegalArgumentException("The array is empty")
    }
    if (arr.size == 1) {
        return arr[0]
    }
    var currentRes = arr[1] - arr[0]
    var currentMin = arr[0]
    for (i in 1 until arr.size) {
        currentRes = max(currentRes, arr[i] - currentMin)
        currentMin = min(currentMin, arr[i])
    }
    return currentRes
}

fun findFrequenciesInSortedArray(array: IntArray): Map<Int, Int> {
    val res = hashMapOf<Int, Int>()
    var frequency = 1
    var i = 1
    while (i < array.size) {
        while (i < array.size && array[i] == array[i - 1]) {
            ++frequency
            ++i
        }
        res.put(array[i - 1], frequency)
        ++i
        frequency = 1
    }
    // handle corner case when last element is not the same as second to last
    // of the array.size == 1
    if (array.size == 1 || array[array.lastIndex] != array[array.lastIndex - 1]) {
        res.put(array[array.lastIndex], 1)
    }

    return res
}

/**
 * Stock problem. Given a list of stock prices corresponding to
 * certain days, find the maximum profit, under condition that you
 * can't sell stock in the same day that you bought it.
 *
 * E.g. days  1  2  3  4   5
 *    prices  1  5  3  8  12
 *    Buy on day 1, sell on day 2 get profit of 4.
 *    Buy on dat 3, sell on day 5, get profit of 9.
 *    Total profit = 13.
 */
fun stockMaxProfitNaive(prices: IntArray): Int {
    return stockMaxProfitRecursiveHelper(prices, 0, prices.lastIndex)
}

private fun stockMaxProfitRecursiveHelper(prices: IntArray, start: Int, end: Int): Int {
    if (end <= start) {
        return 0
    }
    var profit = 0
    for (i in start until end) {
        for (j in i + 1..end) {
            if (prices[j] > prices[i]) {
                val currentMax = prices[j] - prices[i] +
                        stockMaxProfitRecursiveHelper(prices, start, i - 1) +
                        stockMaxProfitRecursiveHelper(prices, j + 1, end)

                profit = max(profit, currentMax)
            }
        }
    }
    return profit
}

fun maxStockPriceEfficient(prices: IntArray): Int {
    var max = 0
    for (i in 1 until prices.size) {
        if (prices[i] > prices[i - 1]) {
            max += prices[i] - prices[i - 1]
        }
    }
    return max
}

/**
 * Tapping water problem.
 * Given an array of non-negative integers that represent bars
 * which serve as barriers for water, find out how many units of
 * water can be contained between these bars.
 *
 * E.g.               input  2 0 2
 * can be represented as     |  |
 *                           |__|
 * So, between these bars we can contain 2 units of water.
 *
 * Time Complexity O(N^2)
 */
fun tappingWaterNaive(bars: IntArray): Int {
    var res = 0
    for (i in 1 until bars.lastIndex) {
        var leftMax = bars[i]
        for (j in 0 until i) {
            leftMax = max(leftMax, bars[j])
        }
        var rightMax = bars[i]
        for (j in i + 1 until bars.size) {
            rightMax = max(rightMax, bars[j])
        }
        res += (min(leftMax, rightMax) - bars[i])
    }
    return res
}

/**
 * Time complexity O(N)
 */
fun tappingWaterEfficient(bars: IntArray): Int {
    if (bars.isEmpty() || bars.size == 1 || bars.size == 2) {
        return 0
    }
    // precompute leftMax and rightMax elements for every element in the array
    val lMaxes = IntArray(bars.size)
    val rMaxes = IntArray(bars.size)
    lMaxes[0] = bars[0]
    for (i in 1 until lMaxes.size) {
        lMaxes[i] = max(bars[i], lMaxes[i - 1])
    }
    rMaxes[rMaxes.lastIndex] = bars[bars.lastIndex]
    for (i in rMaxes.lastIndex - 1 downTo 0) {
        rMaxes[i] = max(bars[i], rMaxes[i + 1])
    }
    var res = 0
    for (i in 1 until bars.lastIndex) {
        res += (min(lMaxes[i], rMaxes[i]) - bars[i])
    }
    return res
}

/**
 * Given an array of zeroes and ones, find maximum number of
 * consecutive ones in the array.
 */
fun maxConsecutiveOnes(arr: IntArray): Int {
    var res = 0
    var counter = 0
    arr.forEach { elem ->
        if (elem == 1) {
            ++counter
            res = max(res, counter)
        } else {
            counter = 0
        }
    }
    return res
}

/**
 * For every element in the array we find the max sum
 * of a subarray that ends with this element.
 * The largest sum will be the result.
 */
fun maxSubarraySum(arr: IntArray): Int {
    if (arr.isEmpty()) {
        throw IllegalArgumentException("The array is empty")
    }
    var maxEnding = arr[0]
    var res = arr[0]
    for (i in 1 until arr.size) {
        maxEnding = max(maxEnding + arr[i], arr[i])
        res = max(res, maxEnding)
    }
    return res
}

/**
 * Given an array of integers, find the maximum length of
 * a subarray, that contains alternating odd and even numbers.
 *
 * Time complexity O(N^2).
 */
fun maxLengthEvenOddSubarrayNaive(arr: IntArray): Int {
    var res = 1
    for (i in arr.indices) {
        var current = 1
        for (j in i + 1 until arr.size) {
            if ((arr[j] % 2 == 0 && arr[j - 1] % 2 != 0) ||
                (arr[j] % 2 != 0 && arr[j - 1] % 2 == 0)
            ) {
                ++current
            } else {
                break
            }
        }
        res = max(res, current)
    }
    return res
}

fun maxLengthEvenOddSubarrayEfficient(arr: IntArray): Int {
    var res = 1
    var current = 1
    for (i in 1 until arr.size) {
        if ((arr[i] % 2 == 0 && arr[i - 1] % 2 != 0) ||
            (arr[i] % 2 != 0 && arr[i - 1] % 2 == 0)
        ) {
            ++current
            res = max(res, current)
        } else {
            current = 1
        }
    }
    return res
}

fun maxSumOfCircularSubarrayNaive(arr: IntArray): Int {
    var res = arr[0]
    for (i in arr.indices) {
        var currentMax = arr[i]
        var currentSum = arr[i]
        for (j in 1 until arr.size) {
            val index = (i + j) % arr.size
            currentSum += arr[index]
            currentMax = max(currentMax, currentSum)
        }
        res = max(res, currentMax)
    }
    return res
}

/**
 * The solution is based on the following idea:
 *  Step 1. find the max sum of a normal subarray
 *  Step 2. find the minimum sum subarray of the array. (this is actually
 *          the difference between the total sum of the array and the minimum
 *          sum subarray)
 *  Step 3. subtract the minimum sum subarray from the sum of entire array.
 *          actually here we invert the array and add the sum of the entire
 *          array to the max sum of the inverted array. That allows to reuse the
 *          function for finding a max sum normal subarray.
 *  Step 4. the result is the max from step 3 and step 1.
 *
 *  TimeComplexity O(N)
 */
fun maxSumOfCircularSubarrayEfficient(arr: IntArray): Int {

    val normalSum = maxNormalSubarray(arr)

    // this is a critical part of the algorithm.
    // if the normal sum subarray is negative it means,
    // the entire array consist of negative numbers and this is actually
    // the largest of them all, so we don't need to proceed any further.
    // this is not only optimization, but a correct solution.
    // Otherwise, there's a corner case, when the algorithm will not
    // work correctly: [-5, -3]. normalSum = -3, arraySum = -8
    // we invert the array and receive [5, 3], maxCircularSum = -8 + 8 = 0
    // result = max(-3, 0) = 0 and this is incorrect!
    if (normalSum < 0) {
        return normalSum
    }

    var arrSum = 0
    for (i in arr.indices) {
        arrSum += arr[i]
        arr[i] = -arr[i]
    }
    val maxCircular = arrSum + maxNormalSubarray(arr)

    return max(normalSum, maxCircular)
}

private fun maxNormalSubarray(arr: IntArray): Int {
    var normalSum = arr[0]
    var maxEnding = arr[0]
    for (i in 1 until arr.size) {
        maxEnding = max(arr[i], maxEnding + arr[i])
        normalSum = max(normalSum, maxEnding)
    }
    return normalSum
}

/**
 * Given an array of integers find a majority element if it exists.
 * A majority element is the one that appears more than arr.size / 2 times.
 *
 * @return any of the indices of the majority element or -1 if none found.
 */
fun majorityElementNaive(arr: IntArray): Int {
    for (i in arr.indices) {
        var count = 1
        for (j in i + 1 until arr.size) {
            if (arr[i] == arr[j]) {
                ++count
            }
        }
        if (count > arr.size / 2) {
            return i
        }
    }
    return -1
}

fun majorityElementEfficient(arr: IntArray): Int {
    // phase 1. Find the candidate.
    var resIndex = 0
    var count = 1
    for (i in 1 until arr.size) {
        if (arr[resIndex] == arr[i]) {
            ++count
        } else {
            --count
        }
        if (count == 0) {
            resIndex = i
            count = 1
        }
    }
    // phase 2. Check if the candidate is the majority element
    count = 0
    arr.forEach { element ->
        if (arr[resIndex] == element) {
            ++count
        }
    }
    if (count > arr.size / 2) {
        return resIndex
    }
    return -1
}

/**
 * Given a binary array (array consisting of either zeroes or ones)
 * the task is to flip all elements to either one or to zero in
 * the minimum number of flips.
 *
 * BUT! Consecutive flip of the numbers is considered as one flip.
 */
fun minFlipsNaive(arr: IntArray): List<Int> {
    var groupsOfZero = 0
    var groupsOfOne = 0
    arr.forEach { elem ->
        if (elem == 0) ++groupsOfZero
        else ++groupsOfOne
    }
    val res = mutableListOf<Int>()
    if (groupsOfOne < groupsOfZero) {
        arr.forEachIndexed { index, elem ->
            if (elem == 1) {
                res.add(index)
            }
        }
    } else {
        arr.forEachIndexed { index, elem ->
            if (elem == 0) {
                res.add(index)
            }
        }
    }
    return res
}

/**
 * The efficient solution is based on the idea that
 * the difference between the count of zero groups and
 * one groups is always either 1 or 0 since we are traversing
 * a binary array.
 *
 * E.g. 1100110011
 * If the array starts with group of 1 and ends with group of 1 then
 * the number of groups of 0 will be less by 1.
 *
 * If the array starts with 1 and ends with 0 then there's an
 * equal number of groups.
 *
 * The conclusion is -> flip only those groups that you meet second
 * in the array.
 */
fun minFlipsEfficient(arr: IntArray): List<Int> {
    val check = if (arr[0] == 1) 0 else 1
    val res = mutableListOf<Int>()
    calculateFlip(res, arr, check)
    return res
}

private fun calculateFlip(res: MutableList<Int>, arr: IntArray, check: Int) {
    arr.forEachIndexed { index, elem ->
        if (elem == check) {
            res.add(index)
        }
    }
}

/**
 * Given an array of integers and a number k,
 * the task is to find the  maximum sum of k consecutive elemens in the
 * array.
 *
 * Time complexity O(n-k)* k
 */
fun windowSlidingNaive(arr: IntArray, k: Int): Int {
    var maxSum = Int.MIN_VALUE
    var i = 0
    while ((i + k - 1) < arr.size) {
        var currentSum = 0
        for (j in 0 until k) {
            currentSum += arr[i + j]
        }
        maxSum = max(maxSum, currentSum)
        ++i
    }
    return maxSum
}

/**
 * Time complexity O(N)
 */
fun windowSlidingEfficient(arr: IntArray, k: Int): Int {
    var currentSum = 0
    for (i in 0 until k) {
        currentSum += arr[i]
    }
    var maxSum = currentSum
    for (j in k until arr.size) {
        // subtract first element of the previous window
        // add the next element
        currentSum += (arr[j] - arr[j - k])
        maxSum = max(maxSum, currentSum)
    }
    return maxSum
}

/**
 * Given an array of integers, a sum and a number k,
 * the task is to find if there's a subarray of size k, whose
 * sum is equal to the given sum.
 */
fun windowSlidingGivenSum(arr: IntArray, k: Int, sum: Int): Boolean {
    var currentSum = 0
    for (i in 0 until k) {
        currentSum += arr[i]
    }
    if (currentSum == sum) return true
    for (j in k until arr.size) {
        currentSum += (arr[j] - arr[j - k])
        if (currentSum == sum) return true
    }
    return false
}

/**
 * Given an unsorted array of non-negative integers find if there's
 * a subarray with a given sum.
 */
fun givenSumSubarray(arr: IntArray, sum: Int): Boolean {
    var currentSum = arr[0]
    var start = 0
    for (end in 1 until arr.size) {
        while (currentSum > sum && start < end - 1) {
            currentSum -= arr[start]
            ++start
        }
        if (currentSum == sum) {
            return true
        }
        currentSum += arr[end]
    }
    return currentSum == sum
}

/**
 * Compute [m] numbers, where every number is the sum of previous [n]
 * numbers.
 *
 * Time Complexity is O(N)
 */
fun nBonacciNumbers(n: Int, m: Int): List<Int> {
    val result = mutableListOf<Int>()
    if (m < n) {
        return result
    }
    for (i in 0 until n - 1) {
        result.add(0)
    }
    result.add(n - 1, 1)
    result.add(n, 1)
    for (i in n + 1 until m) {
        // here we get 2 * result[i-1], because we need to do the following:
        // result[i-1] + result[i - 1] - result[i - n - 1]
        result.add(i, 2 * result[i - 1] - result[i - n - 1])
    }
    return result
}


/**
 * Given a fixed size array of integers, find the most efficient
 * way to handle queries of type getSum(0, 2), getSum(3, 6), where
 * integers in the queried indicate start and end indices (inclusive)
 * in the array.
 *
 * Requirements - every query must run in O(1), a preprocessing
 * is allowed and it must run in O(N) time.
 */
val array = IntArray(100) { it }
fun prefixSum(arr: IntArray): IntArray {
    val newArray = IntArray(arr.size)
    newArray[0] = arr[0]
    for (i in 1 until arr.size) {
        newArray[i] = newArray[i - 1] + arr[i]
    }
    return newArray
}

fun getSum(prefixSum: IntArray, start: Int, end: Int): Int {
    return if (start != 0) {
        prefixSum[end] - prefixSum[start - 1]
    } else {
        prefixSum[end]
    }
}

/**
 * Given an array of integers, find if it has an equilibrium point,
 * i.e. the element in the array such that the sum of elements before it
 * is equal to the sum of elements after it.
 *
 * @return index of the equilibrium point or -1 if there's none.
 */
fun indexOfEquilibriumPoint(arr: IntArray): Int {
    var totalSum = arr.sum()
    var leftSum = 0
    for (i in arr.indices) {
        if (leftSum == totalSum - arr[i]) {
            return i
        }
        leftSum += arr[i]
        totalSum -= arr[i]
    }
    return -1
}

/**
 * Given N number of ranges, find the most frequent element in the
 * ranges. The ranges' start and end elements are stored in two
 * arrays of integers at correspoining indices. E.g.
 * left = [1,2,5,15]
 * right = [5,8,7,18]
 *
 * The ranges are:
 *                  1-5 {1,2,3,4,5}
 *                  2-8 {2,3,4,5,6,7,8}
 *                  5-7 {5,6,7}
 *                  15-18 {15,16,17,18}
 * We may assume that left[i] <= right[i] and 0 <= left[i] || right[i] <1000
 * The output here is 5.
 */
fun mostFrequentInRanges(left: IntArray, right: IntArray): Int {
    // stores beginning and end of all possible ranges
    // from left and right arrays
    val arr = IntArray(1000) { 0 }
    for (i in left.indices) {
        arr[left[i]]++
        arr[right[i] + 1]--
    }
    var max = arr[0]
    var res = 0
    // compute frequencies of all elements in all ranges
    for (i in 1 until 1000) {
        arr[i] += arr[i - 1]
        if (max < arr[i]) {
            max = arr[i]
            res = i
        }
    }
    return res
}


fun mergeSortedArrays(left: IntArray, right: IntArray): IntArray {
    var i = 0
    var j = 0
    var k = 0
    val res = IntArray(left.size + right.size)
    while (i < left.size && j < right.size) {
        if (left[i] < right[j]) {
            res[k++] = left[i++]
        } else {
            res[k++] = right[j++]
        }
    }
    while (i < left.size) {
        res[k++] = left[i++]
    }
    while (j < right.size) {
        res[k++] = right[j++]
    }
    return res
}

/**
 * Given an array a[ ] of size N. The task is to find the median
 * and mean of the array elements. Mean is average of the numbers
 * and median is the element which is smaller than half of the
 * elements and greater than remaining half.  If there are odd
 * elements, the median is simply the middle element in the sorted
 * array. If there are even elements, then the median is floor of
 * average of two middle numbers in the sorted array. If mean is
 * floating point number, then we need to print floor of it.
 *
 * Example 1:
 * Input:
 * N = 5
 * a[] = {1, 2, 19, 28, 5}
 * Output: 11 5
 * Explanation: For array of 5 elements,
 * mean is (1 + 2 + 19  + 28  + 5)/5 = 11.
 * Median is 5 (middle element after
 * sorting)
 *
 * Example 2:
 * N = 4
 * a[] = {2, 8, 3, 4}
 * Output: 4 3
 * Explanation: For array of 4 elements,
 * mean is floor((2 + 8 + 3 + 4)/4) = 4.
 * Median is floor((4 + 3)/2) = 3
 */
fun meanOfArray(arr: IntArray): Int {
    return arr.sum() / arr.size
}

fun medianOfArray(arr: IntArray): Int {
    arr.sort()
    return if (arr.size % 2 == 0) {
        val l = arr.size / 2 - 1
        val r = arr.size / 2
        (arr[l] + arr[r]) / 2
    } else {
        arr[arr.size / 2]
    }
}

fun largestAndSecondLargest(arr: IntArray): List<Int> {
    val result = mutableListOf<Int>()
    var max = Int.MIN_VALUE
    var secondMax = Int.MIN_VALUE
    for (i in arr.indices) {
        if (arr[i] > max) {
            secondMax = max
            max = arr[i]
        } else if (arr[i] > secondMax && arr[i] != max) {
            secondMax = arr[i]
        }
    }
    if (max == secondMax) {
        secondMax = -1
    }
    result.add(max)
    result.add(secondMax)
    return result
}

/**
 * Given an array arr[] of n positive integers.
 * The task is to find the maximum for every adjacent pairs in the array.
 *
 * Example 1:
 * Input:
 * n = 6
 * arr[] = {1,2,2,3,4,5}
 * Output: 2 2 3 4 5
 * Explanation: Maximum of arr[0] and arr[1]
 * is 2, that of arr[1] and arr[2] is 2, ...
 * and so on. For last two elements, maximum
 * is 5.
 *
 * Example 2:
 * Input:
 * n = 2
 * arr[] = {5, 5}
 * Output: 5
 * Explanation: We only have two elements
 * so max of 5 and 5 is 5 only.
 */
fun maximumAdjacent(arr: IntArray): List<Int> {
    if (arr.isEmpty()) return emptyList()
    if (arr.size == 1) return listOf(arr[0])
    var currentMax = arr[0]
    val res = mutableListOf<Int>()
    for (i in 1 until arr.size) {
        currentMax = max(currentMax, arr[i])
        res.add(currentMax)
        currentMax = arr[i]
    }
    return res
}

/**
 * Given an array arr[] of positive integers of size N.
 * Reverse every sub-array group of size K.
 *
 * Example 1:
 * Input:
 * N = 5, K = 3
 * arr[] = {1,2,3,4,5}
 * Output: 3 2 1 5 4
 * Explanation: First group consists of elements
 * 1, 2, 3. Second group consists of 4,5.
 *
 * Example 2:
 * Input:
 * N = 4, K = 3
 * arr[] = {5,6,8,9}
 * Output: 8 6 5 9
 */
fun reverseInGroups(arr: IntArray, k: Int) {
    var start = 0
    var end = k - 1
    for (i in arr.indices step k) {
        while (start <= end) {
            val tmp = arr[start]
            arr[start] = arr[end]
            arr[end] = tmp
            ++start
            --end
        }
        start = i + k
        end = start + k - 1
        if (end >= arr.size) {
            end = arr.size - 1
        }
    }
}

/**
 * Given an array Arr of n integers arranged in a circular fashion.
 * Your task is to find the minimum absolute difference between adjacent elements.
 *
 * Example 1:
 * Input:
 * n = 7
 * Arr[] = {8,-8,9,-9,10,-11,12}
 * Output: 4
 * Explanation: The absolute difference
 * between adjacent elements in the given
 * array are as such: 16 17 18  19 21 23 4
 * (first and last). Among the calculated
 * absolute difference the minimum is 4.
 *
 * Example 2:
 * Input:
 * n = 8
 * Arr[] = {10,-3,-4,7,6,5,-4,-1}
 * Output: 1
 * Explanation: The absolute difference
 * between adjacent elements in the given
 * array are as such: 13 1 11 1 1 9 3 11
 * (first and last).  Among the calculated
 * absolute difference, the minimum is 1.
 */
fun minAdjDiff(arr: IntArray): Int {
    if (arr.isEmpty()) throw IllegalArgumentException("Array is empty")
    if (arr.size == 1) return arr[0]

    var min = abs(arr[0] - arr[1])
    for (i in 2 until arr.size) {
        val currentMin = abs(arr[i - 1] - arr[i])
        min = min(min, currentMin)
    }
    val lastMin = abs(arr[arr.lastIndex] - arr[0])
    min = min(min, lastMin)
    return min
}

/**
 * Given n integer ranges, the task is to find the maximum occurring
 * integer in these ranges. If more than one such integer exits, find
 * the smallest one. The ranges are given as two arrays L[] and R[].
 * L[i] consists of starting point of range and R[i] consists of corresponding
 * end point of the range.
 *
 * For example consider the following ranges.
 * L[] = {2, 1, 3}, R[] = {5, 3, 9)
 * Ranges represented by above arrays are.
 * [2, 5] = {2, 3, 4, 5}
 * [1, 3] = {1, 2, 3}
 * [3, 9] = {3, 4, 5, 6, 7, 8, 9}
 * The maximum occurred integer in these ranges is 3.
 *
 * Example 1:
 * Input:
 * n = 4
 * L[] = {1,4,3,1}
 * R[] = {15,8,5,4}
 * Output: 4
 * Explanation: The given ranges are [1,15]
 * [4, 8] [3, 5] [1, 4]. The number that
 * is most common or appears most times in
 * the ranges is 4.
 *
 * Example 2:
 * Input:
 * n = 5
 * L[] = {1,5,9,13,21}
 * R[] = {15,8,12,20,30}
 * Output: 5
 * Explanation: The given ranges are
 * [1,15] [5, 8] [9, 12] [13, 20]
 * [21, 30]. The number that is most
 * common or appears most times in
 * the ranges is 5.
 *
 * Expected Time Complexity: O(n+maxx).
 * Expected Auxiliary Space: O(maxx).
 * -maxx is maximum element in R[]
 *
 *
 * Constraints:
 * 1 ≤ n ≤ 106
 * 0 ≤ L[i], R[i] ≤ 106
 *
 */
fun maxOccurred(left: IntArray, right: IntArray, ceiling: Int): Int {
    val prefixSum = IntArray(ceiling + 2) { 0 }
    for (i in left.indices) {
        prefixSum[left[i]]++
        prefixSum[right[i] + 1]--
    }
    var max = prefixSum[0]
    var res = 0
    for (i in 1..ceiling) {
        prefixSum[i] += prefixSum[i - 1]
        if (max < prefixSum[i]) {
            max = prefixSum[i]
            res = i
        }
    }
    return res
}

/**
 * Given a sorted array arr[] of distinct integers.
 * Sort the array into a wave-like array and return it
 * In other words, arrange the elements into a sequence
 * such that arr[1] >= arr[2] <= arr[3] >= arr[4] <= arr[5].....
 *
 * Example 1:
 * Input:
 * n = 5
 * arr[] = {1,2,3,4,5}
 * Output: 2 1 4 3 5
 * Explanation: Array elements after
 * sorting it in wave form are
 * 2 1 4 3 5.
 *
 * Example 2:
 * Input:
 * n = 6
 * arr[] = {2,4,7,8,9,10}
 * Output: 4 2 8 7 10 9
 * Explanation: Array elements after
 * sorting it in wave form are
 * 4 2 8 7 10 9.
 *
 *
 * Expected Time Complexity: O(n).
 * Expected Auxiliary Space: O(1).
 *
 * Constraints:
 * 1 ≤ n ≤ 106
 * 0 ≤ arr[i] ≤107
 */
fun convertToWave(arr: IntArray) {
    if (arr.size == 1) return
    var left = 0
    var right = 1
    var index = 2
    for (i in 2 until arr.size step 2) {
        swapp(arr, left, right)
        left = i
        right = left + 1
        index += 2
    }
    if (index == arr.size) {
        left = arr.lastIndex - 1
        right = arr.lastIndex
        swapp(arr, left, right)
    }
}

private fun swapp(arr: IntArray, from: Int, to: Int) {
    val tmp = arr[from]
    arr[from] = arr[to]
    arr[to] = tmp
}


/**
 * Given an array A[] of N positive integers which can contain
 * integers from 1 to P where elements can be repeated or can be
 * absent from the array. Your task is to count the frequency of
 * all elements from 1 to N.
 *
 * Example 1:
 * Input:
 * N = 5
 * arr[] = {2, 3, 2, 3, 5}
 * P = 5
 * Output:
 * 0 2 2 0 1
 * Explanation:
 * Counting frequencies of each array element
 * We have:
 * 1 occurring 0 times.
 * 2 occurring 2 times.
 * 3 occurring 2 times.
 * 4 occurring 0 times.
 * 5 occurring 1 time.
 *
 * Example 2:
 * Input:
 * N = 4
 * arr[] = {3,3,3,3}
 * P = 3
 * Output:
 * 0 0 4 0
 * Explanation:
 * Counting frequencies of each array element
 * We have:
 * 1 occurring 0 times.
 * 2 occurring 0 times.
 * 3 occurring 4 times.
 * 4 occurring 0 times.
 *
 * Constraints:
 * 1 ≤ N ≤ 10^5
 * 1 ≤ P ≤ 4*10^4
 * 1 <= A[i] <= P
 *
 * The idea is to traverse the given array, use elements as an index
 * and store their counts at the index. Consider the basic approach,
 * a Hashmap of size n is needed and the array is also of size n.
 * So the array can be used as a hashmap, all the elements of the
 * array are from 1 to n, i.e. all are positive elements. So the
 * frequency can be stored as negative. This might lead to a problem.
 * Let i-th element be a then the count should be stored at array[a-1],
 * but when the frequency will be stored the element will be lost.
 * To deal with this problem, first, replace the ith element by array[a-1]
 * and then put -1 at array[a-1]. So our idea is to replace the element
 * by frequency and store the element in the current index and if the
 * element at array[a-1] is already negative, then it is already replaced
 * by a frequency so decrement array[a-1].
 */
fun frequencyCount(arr: IntArray) {
    val N = arr.size
    var i = 0
    // Traverse all array elements
    while (i < N) {
        // If this element is already processed,
        // then nothing to do
        if (arr[i] <= 0) {
            ++i
            continue
        }
        // Find index corresponding to this element
        // For example, index for 5 is 4
        val elementIndex = arr[i] - 1
        // This if condition is to make sure that the element is within the
        // range coz if it's not then we consider the element never existed
        // (make it 0)
        if (elementIndex > N - 1) {
            arr[i] = 0
            ++i
            continue
        }
        // If the elementIndex has an element that is not
        // processed yet, then first store that element
        // to arr[i] so that we don't lose anything.
        if (arr[elementIndex] > 0) {
            arr[i] = arr[elementIndex]
            // After storing arr[elementIndex], change it
            // to store initial count of 'arr[i]'
            arr[elementIndex] = -1
        } else {
            // If this is NOT first occurrence of arr[i],
            // then decrement its count.
            arr[elementIndex]--
            // And initialize arr[i] as 0 means the element
            // 'i+1' is not seen so far
            arr[i] = 0
            ++i
        }
    }
    for (j in arr.indices) {
        arr[j] = arr[j] * -1
    }
}

/**
 * You are given an array arr[] of N integers including 0.
 * The task is to find the smallest positive number missing from the array.
 *
 * Example 1:
 * Input:
 * N = 5
 * arr[] = {1,2,3,4,5}
 * Output: 6
 * Explanation: Smallest positive missing
 * number is 6.
 *
 * Example 2:
 * Input:
 * N = 5
 * arr[] = {0,-10,1,3,-20}
 * Output: 2
 * Explanation: Smallest positive missing
 * number is 2.
 *
 * To mark presence of an element x, we change the value at the index x to negative.
 * But this approach doesn’t work if there are non-positive (-ve and 0) numbers.
 * So we segregate positive from negative numbers as first step and then apply
 * the approach.
 *
 * Following is the two step algorithm.
 * 1) Segregate positive numbers from others i.e., move all non-positive numbers
 * to left side. In the following code, segregate() function does this part.
 *
 * 2) Now we can ignore non-positive elements and consider only the part of
 * array which contains all positive elements. We traverse the array containing
 * all positive numbers and to mark presence of an element x, we change the sign
 * of value at index x to negative. We traverse the array again and print the
 * first index which has positive value. In the following code,
 * findMissingPositive() function does this part. Note that in findMissingPositive,
 * we have subtracted 1 from the values as indexes start from 0.
 */
fun smallestPositiveMissingNumber(arr: IntArray): Int {
    return findMissingPositive(arr, segregate(arr))
}

/**
 * Puts all negative elements and zeroes in the array to the left
 * side of the array and returns the number of such elements.
 */
private fun segregate(arr: IntArray): Int {
    var result = 0
    for (i in arr.indices) {
        if (arr[i] <= 0) {
            val tmp = arr[i]
            arr[i] = arr[result]
            arr[result] = tmp
            ++result
        }
    }
    return result
}

private fun findMissingPositive(arr: IntArray, startOfPositive: Int): Int {
    val size = arr.size - startOfPositive
    for (i in startOfPositive until arr.size) {
        val x = abs(arr[i])
        if (x - 1 < size && arr[x + startOfPositive - 1] > 0) {
            arr[x + startOfPositive - 1] = -arr[x + startOfPositive - 1]
        }
    }
    for (i in startOfPositive until arr.size) {
        if (arr[i] > 0) {
            return i - startOfPositive + 1
        }
    }
    return size + 1
}

/**
 * Given a sorted array of positive integers.
 * Your task is to rearrange  the array elements alternatively i.e first
 * element should be max value, second should be min value, third should
 * be second max, fourth should be second min and so on.
 *
 * Example 1:
 * Input:
 * N = 6
 * arr[] = {1,2,3,4,5,6}
 * Output: 6 1 5 2 4 3
 * Explanation: Max element = 6, min = 1,
 * second max = 5, second min = 2, and
 * so on... Modified array is : 6 1 5 2 4 3.
 *
 * Example 2:
 * Input:
 * N = 11
 * arr[]={10,20,30,40,50,60,70,80,90,100,110}
 * Output:110 10 100 20 90 30 80 40 70 50 60
 * Explanation: Max element = 110, min = 10,
 * second max = 100, second min = 20, and
 * so on... Modified array is :
 * 110 10 100 20 90 30 80 40 70 50 60.
 *
 * Expected Time Complexity: O(N).
 * Expected Auxiliary Space: O(1).
 *
 * The idea is to use multiplication and modular trick to store two elements at an index.
 * How does expression “arr[i] += arr[max_index] % max_element * max_element” work ?
 * The purpose of this expression is to store two elements at index arr[i]. arr[max_index]
 * is stored as multiplier and “arr[i]” is stored as remainder. For example in {1 2 3 4 5 6 7 8 9},
 * max_element is 10 and we store 91 at index 0. With 91, we can get original element as 91%10 and
 * new element as 91/10.
 */
fun rearrangeAlternately(arr: IntArray) {
    var minIndex = 0
    var maxIndex = arr.lastIndex
    val maxElem = arr.last() + 1
    for (i in arr.indices) {
        if (i and 1 == 0) { // even
            arr[i] += (arr[maxIndex] % maxElem) * maxElem
            maxIndex--
        } else { // odd
            arr[i] += (arr[minIndex] % maxElem) * maxElem
            minIndex++
        }
    }
    for (i in arr.indices) {
        arr[i] = arr[i] / maxElem
    }
}

/**
 * Given an array arr[] of size N where every element is in the range from 0 to n-1.
 * Rearrange the given array so that arr[i] becomes arr[arr[i]].
 *
 * Example 1:
 * Input:
 * N = 2
 * arr[] = {1,0}
 * Output: 0 1
 * Explanation:
 * arr[arr[0]] = arr[1] = 0.
 * arr[arr[1]] = arr[0] = 1.
 *
 * Example 2:
 * Input:
 * N = 5
 * arr[] = {4,0,2,1,3}
 * Output: 3 4 2 0 1
 * Explanation:
 * arr[arr[0]] = arr[4] = 3.
 * arr[arr[1]] = arr[0] = 4.
 * and so on.
 *
 * Expected Time Complexity: O(N)
 * Expected Auxiliary Space: O(1)
 */
fun arrange(arr: IntArray) {
    val maxElem = arr.size
    for (i in arr.indices) {
        arr[i] += (arr[arr[i]] % maxElem) * maxElem
    }
    for (i in arr.indices) {
        arr[i] = arr[i] / maxElem
    }
}

/**
 * Given an array A[] of N positive integers.
 * The task is to find the maximum of j - i subjected to
 * the constraint of A[i] < A[j] and i < j.
 *
 * Example 1:
 * Input:
 * N = 2
 * A[] = {1, 10}
 * Output:
 * 1
 * Explanation:
 * A[0]<A[1] so (j-i) is 1-0 = 1.
 *
 * Example 2:
 * Input:
 * N = 9
 * A[] = {34, 8, 10, 3, 2, 80, 30, 33, 1}
 * Output:
 * 6
 * Explanation:
 * In the given array A[1] < A[7]
 * satisfying the required
 * condition(A[i] <= A[j]) thus giving
 * the maximum difference of j - i
 * which is 6(7-1).
 *
 * Expected Time Complexity: O(N)
 * Expected Auxiliary Space: O(N)
 *
 * Constraints:
 * 1 ≤ N ≤ 10^7
 * 0 ≤ A[i] ≤ 10^9
 */
fun maxIndexDiffNaive(arr: IntArray): Int {
    var maxDiff = -1
    for (i in arr.indices) {
        for (j in arr.lastIndex downTo i) {
            if (arr[j] > arr[i] && maxDiff < (j - i)) {
                maxDiff = j - i
            }
        }
    }
    return maxDiff
}

/**
 * To solve this problem, we need to get two optimum indexes
 * of arr[]: left index i and right index j. For an element arr[i],
 * we do not need to consider arr[i] for left index if there is an
 * element smaller than arr[i] on left side of arr[i]. Similarly,
 * if there is a greater element on right side of arr[j] then we do
 * not need to consider this j for right index. So we construct two
 * auxiliary arrays LMin[] and RMax[] such that LMin[i] holds the smallest
 * element on left side of arr[i] including arr[i], and RMax[j] holds
 * the greatest element on right side of arr[j] including arr[j].
 * After constructing these two auxiliary arrays, we traverse both
 * of these arrays from left to right. While traversing LMin[] and
 * RMax[] if we see that LMin[i] is greater than RMax[j], then we must
 * move ahead in LMin[] (or do i++) because all elements on left of
 * LMin[i] are greater than or equal to LMin[i]. Otherwise we must
 * move ahead in RMax[j] to look for a greater j – i value.
 *
 * Lets consider any example [7 3 1 8 9 10 4 5 6]
 * what is maxRight ?
 * Filling from right side 6 is first element now 6 > 5 so again we fill 6 till we reach 10 > 6 :
 * [10 10 10 10 10 10 6 6 6] this is maxR
 * [7 3 1 1 1 1 1 1 1 ] this is minL
 *
 * now we see that how to reach answer from these to and its proof !!!
 * lets compare first elements of the arrays now we see 10 > 7,
 * now we increase maxR by 1 till it becomes lesser than 7 i.e at index 5
 * hence answer till now is. 5-0 = 5
 *
 * now we will increase minL we get 3 which is lesser than 6 so we increase maxR till it
 * reaches last index and the answer becomes 8-1= 7
 * so we see how we are getting correct answer.
 *
 * As we need the max difference j – i such that A[i]<= A[j], hence we do not need
 * to consider element after the index j and element before index i.
 * in previous hint, make 2 arrays,
 *
 * First, will store smallest occurring element before the element
 * Second, will store largest occurring element after the element
 * Traverse the Second array, till the element in second array is larger than or equal
 * to First array, and store the index difference. And if it becomes smaller,
 * traverse the first array till it again becomes larger.
 * And store the max difference of this index difference.
 */
fun maxIndexDiffEfficient(arr: IntArray): Int {
    val size = arr.size
    val leftMin = IntArray(size)
    val rightMax = IntArray(size)
    leftMin[0] = arr[0]
    rightMax[arr.lastIndex] = arr[arr.lastIndex]
    for (i in 1 until size) {
        leftMin[i] = min(leftMin[i - 1], arr[i])
    }

    for (j in arr.lastIndex - 1 downTo 0) {
        rightMax[j] = max(rightMax[j + 1], arr[j])
    }
    var maxDiff = -1
    var i = 0
    var j = 0
    while (i < size && j < size) {
        if (leftMin[i] <= rightMax[j]) {
            maxDiff = max(maxDiff, j - i)
            j++
        } else {
            i++
        }
    }
    return maxDiff
}

/**
 * The cost of stock on each day is given in an array A[] of size N.
 * Find all the days on which you buy and sell the stock so that in
 * between those days your profit is maximum.
 *
 * Note: There may be multiple possible solutions. Return any one of
 * them. Any correct solution will result in an output of 1, whereas
 * wrong solutions will result in an output of 0.
 *
 * Example 1:
 * Input:
 * N = 7
 * A[] = {100,180,260,310,40,535,695}
 * Output:
 * 1
 * Explanation:
 * One possible solution is (0 3) (4 6)
 * We can buy stock on day 0,
 * and sell it on 3rd day, which will
 * give us maximum profit. Now, we buy
 * stock on day 4 and sell it on day 6.
 *
 * Example 2:
 * Input:
 * N = 5
 * A[] = {4,2,2,2,4}
 * Output:
 * 1
 * Explanation:
 * There are multiple possible solutions.
 * one of them is (3 4)
 * We can buy stock on day 3,
 * and sell it on 4th day, which will
 * give us maximum profit.
 */
fun stockBuyAndSell(arr: IntArray): List<List<Int>> {
    val result = mutableListOf<List<Int>>()
    var i = 1
    // traverse the entire array starting from 1
    while (i < arr.size) {
        val days = mutableListOf<Int>()
        // if element at index i - 1 (on the first traversal the index = 0)
        // is less than current element
        if (arr[i - 1] < arr[i]) {
            // add this index as the day of buying a stock
            days.add(i - 1)
        }
        // move to the next day
        ++i
        // while we see that the price is lower than on the previous day
        // we go on
        while (i < arr.size) {
            if (arr[i - 1] <= arr[i]) {
                ++i
            } else {
                // when found the element that is less than the previous element
                break
            }
        }
        // add previous element as the day to sell stocks, since
            // the price is highest
        days.add(i - 1)
        // add the days to the result
        result.add(days)
        ++i
    }
    return result
}

/**
 * Given an array arr[] of N distinct integers, check if this array
 * is Sorted (non-increasing or non-decreasing) and Rotated counter-clockwise.
 * Note that input array may be sorted in either increasing or decreasing order, then rotated.
 * A sorted array is not considered as sorted and rotated, i.e.,
 * there should be at least one rotation.
 *
 * Example 1:
 * Input:
 * N = 4
 * arr[] = {3,4,1,2}
 * Output: Yes
 * Explanation: The array is sorted
 * (1, 2, 3, 4) and rotated twice
 * (3, 4, 1, 2).
 *
 * Example 2:
 * Input:
 * N = 3
 * arr[] = {1,2,3}
 * Output: No
 * Explanation: The array is sorted
 * (1, 2, 3) is not rotated.
 *
 * The idea is simple. If array is sorted and rotated , elements are either
 * increasing or decreasing, but with one exception. So we count how many
 * times the element is greater then its previous element, and how many times
 * the element is smaller then its previous element.
 *
 * The special case is when array is sorted but not rotated. for this check
 * last element with first element specially.
 *
 * - Take two variable say x and y, initialized as 0.
 * - Now traverse array.
 * - If we find previous element is smaller then current, we increase x by one.
 * - Else If we find previous element is greater then current we increase y by one.
 * - After traversal if any of x and y is not equals to 1, return false.
 * - If any of x or y is 1, then compare last element with first element
 *   (0th element as current, and last element as previous.) i.e. if
 *   last element is greater increase y by one else increase x by one.
 * - Again check for x and y if any one is equals to one return true. i.e.
 *   Array is sorted and rotated. Else return false.
 */
fun checkRotatedAndSorted(arr: IntArray): Boolean {
    if (arr.isEmpty() || arr.size == 1 || arr.size == 2) {
        return false
    }

    var x = 0
    var y = 0
    for (i in 0 until arr.size - 1) {
        if (arr[i] < arr[i + 1]) {
            ++x
        } else {
            ++y
        }
    }
    // If till now either x or y is greater than 1 means
    // array is not sorted. If either x or y is zero
    // means array is not rotated.
    if (y == 1 || x == 1) {
        if (arr[arr.lastIndex] < arr[0]) {
            ++x
        } else {
            ++y
        }
        if (x == 1 || y ==1) {
            return true
        }
    }
    return false
}
























