package geeks_for_geeks.algorithms.arrays

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
    rMaxes[lMaxes.lastIndex] = bars[bars.lastIndex]
    for (i in lMaxes.lastIndex - 1 downTo 0) {
        rMaxes[i] = max(bars[i], rMaxes[i + 1])
    }
    var res = 0
    for (i in 1 until bars.lastIndex) {
        res += (min(lMaxes[i], rMaxes[i]) - bars[i])
    }
    return res
}
































