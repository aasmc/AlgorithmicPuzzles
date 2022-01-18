package geeks_for_geeks.algorithms.searching

import kotlin.math.abs
import kotlin.math.max
import kotlin.math.min

/**
 * Searches for a [target] in an array sorted in increasing order.
 *
 * @return index of any element equal to [target] or -1 if none.
 */
fun binarySearch(arr: IntArray, target: Int): Int {
    var start = 0
    var end = arr.lastIndex
    while (start <= end) {
        val medium = start + (end - start) / 2
        if (arr[medium] == target) {
            return medium
        }
        if (arr[medium] < target) {
            start = medium + 1
        } else {
            end = medium - 1
        }
    }
    return -1
}

fun binarySearchRecursive(arr: IntArray, target: Int): Int {
    return binarySearchRecursiveHelper(arr, target, 0, arr.lastIndex)
}

private fun binarySearchRecursiveHelper(arr: IntArray, target: Int, start: Int, end: Int): Int {
    if (start > end) return -1
    val middle = start + (end - start) / 2
    if (arr[middle] == target) return middle
    return if (arr[middle] < target) {
        binarySearchRecursiveHelper(arr, target, middle + 1, end)
    } else {
        binarySearchRecursiveHelper(arr, target, start, middle - 1)
    }
}

fun firstOccurrenceInSortedArray(arr: IntArray, target: Int): Int {
    var start = 0
    var end = arr.lastIndex
    while (start <= end) {
        val mid = start + (end - start) / 2
        if (arr[mid] == target) {
            if (mid > 0 && arr[mid - 1] == target) {
                end = mid
            } else {
                return mid
            }
        } else
            if (arr[mid] < target) {
                start = mid + 1
            } else {
                end = mid - 1
            }
    }
    return -1
}

fun lastOccurrenceInSortedArray(arr: IntArray, target: Int): Int {
    var start = 0
    var end = arr.lastIndex
    while (start <= end) {
        val mid = start + (end - start) / 2
        if (arr[mid] == target) {
            if (mid < arr.lastIndex && arr[mid + 1] == target) {
                start = mid + 1
            } else {
                return mid
            }
        } else
            if (arr[mid] < target) {
                start = mid + 1
            } else {
                end = mid - 1
            }
    }
    return -1
}

/**
 * Time complexity is O(logN + k) where k is the number of occurrences of
 * [target]. In the worst case it is = O(N) when the number of occurrences of
 * [target] == arr.size.
 */
fun countOccurrencesInSortedArray(arr: IntArray, target: Int): Int {
    var start = 0
    var end = arr.lastIndex
    while (start <= end) {
        val mid = start + (end - start) / 2
        if (arr[mid] == target) {
            var result = 1
            var toLeft = mid - 1
            while (toLeft >= 0 && arr[toLeft] == target) {
                ++result
                --toLeft
            }
            var toRight = mid + 1
            while (toRight <= arr.lastIndex && arr[toRight] == target) {
                ++result
                ++toRight
            }
            return result
        }
        if (arr[mid] < target) {
            start = mid + 1
        } else {
            end = mid - 1
        }
    }
    return 0
}

fun countOccurrencesInSortedArrayEfficient(arr: IntArray, target: Int): Int {
    val first = firstOccurrenceInSortedArray(arr, target)
    if (first == -1) {
        return 0
    }
    val last = lastOccurrenceInSortedArray(arr, target)
    return (last - first + 1)
}

fun countOnesInBinarySortedArray(arr: IntArray): Int {
    val first = firstOccurrenceInSortedArray(arr, 1)
    if (first == -1) {
        return 0
    }
    return arr.size - first
}

/**
 * Time complexity O(sqrt(N))
 */
fun squareRootFloorNaive(num: Int): Int {
    var i = 1
    while (i * i <= num) {
        ++i
    }
    return i - 1
}

/**
 * Time complexity O(logN)
 */
fun squareRootFloorEfficient(num: Int): Int {
    var start = 1
    var end = num
    var answer = -1
    while (start <= end) {
        val mid = start + (end - start) / 2
        val midSquare = mid * mid
        if (midSquare == num) {
            return mid
        }
        if (midSquare < num) {
            answer = mid
            start = mid + 1
        } else {
            end = mid - 1
        }
    }
    return answer
}

/**
 * Unbounded binary search algorithm.
 */
fun searchInInfiniteArray(arr: IntArray, target: Int): Int {
    if (arr[0] == target) return 0
    var i = 1
    // still need to check for array bounds, because arrays are
    // finite in kotlin
    while (i < arr.size && arr[i] < target) {
        i *= 2
    }
    if (i >= arr.size) return -1
    if (arr[i] == target) return i
    var start = i / 2 + 1
    var end = i - 1
    while (start <= end) {
        val mid = start + (end - start) / 2
        if (arr[mid] == target) return mid
        if (arr[mid] < target) {
            start = mid + 1
        } else {
            end = mid - 1
        }
    }
    return -1
}

fun searchInSortedRotatedArray(array: IntArray, target: Int): Int {
    var start = 0
    var end = array.lastIndex
    while (start <= end) {
        val mid = start + (end - start) / 2
        if (array[mid] == target) return mid
        // determine which half of the array is sorted
        if (array[start] < array[mid]) { // left half is sorted
            if (target >= array[start] && target < array[mid]) { // target is in this range start..mid
                end = mid - 1
            } else { // target is not in the range start..mid
                start = mid + 1
            }
        } else { // right half is sorted
            if (target > array[mid] && target <= array[end]) { // target is in range mid..end
                start = mid + 1
            } else { // target is not in range mid..end
                end = mid - 1
            }
        }
    }
    return -1
}

/**
 * An element is called a peak element if it is not smaller than
 * its neighbours.
 *
 * Here we can apply a binary search, because the condition states,
 * that a peak element is greater than or equal to its neighbours.
 * It means that after finding the middle element in the array - step 1
 * in binary search, we can determine if it is peak or not, and if not
 * then we can find an element that is greater than middle element.
 * That element will be either to the left or to the right of the middle
 * element. And therefore we can safely say that there will be a peak
 * element on the side that contains an element that is greater than the middle one.
 */
fun findPeakElementInArray(arr: IntArray): Int {
    var start = 0
    var end = arr.lastIndex
    while (start <= end) {
        val middle = start + (end - start) / 2
        if ((middle == 0 || arr[middle - 1] <= arr[middle]) &&
            (middle == arr.lastIndex || arr[middle + 1] <= arr[middle])
        ) {
            return arr[middle]
        }
        if (middle > 0 && arr[middle - 1] >= arr[middle]) {
            end = middle - 1
        } else {
            start = middle + 1
        }
    }
    // unreachable
    throw IllegalStateException(
        "This should never happen. " +
                "There's always a peak element in an array of integers"
    )
}

/**
 * Given an unsorted array of integers and a number x, find if there's
 * a pair of elements in the array whose sum is equal to x.
 * @return a pair of indices, pointing to the numbers, whose sum == x.
 *         if there's no such elements, return a pair containing -1, -1.
 */
fun findPairWithSumEqualToXInUnsortedArray(arr: IntArray, x: Int): Pair<Int, Int> {
    val map = hashMapOf<Int, Pair<Int, Int>>()
    for (i in arr.indices) {
        map[x - arr[i]] = i to arr[i]
    }
    var first = -1
    var second = -1
    var firstFound = false
    var secondFound = false
    for (i in arr.indices) {
        val pair = map[arr[i]]
        if (pair != null) {
            if (!firstFound) {
                firstFound = true
                first = pair.first
            } else if (!secondFound) {
                secondFound = true
                second = pair.first
            }
        }
        if (firstFound && secondFound) break
    }
    if (firstFound && secondFound) {
        return first to second
    }
    return -1 to -1
}

/**
 * Use two pointers: left and right.
 * Step 1. Compare the sum of elements at indices left and right.
 *   - if equal to x, then return pair of left to right
 *   - if greater than x, then move pointer that points to the greater of the elements
 *   - if smaller than x, then move pointer that points to the smaller of the elements
 */
fun findPairWithSumEqualToXInSortedArray(arr: IntArray, x: Int): Pair<Int, Int> {
    var left = 0
    var right = arr.lastIndex
    while (left < right) {
        val sum = arr[left] + arr[right]
        if (sum == x) {
            return left to right
        }
        if (sum > x) {
            --right
        } else {
            ++left
        }
    }
    return -1 to -1
}

/**
 * Given a sorted array of integers and number x, find if there's a
 * triplet with sum = x.
 *
 * @return first triplet in the array whose sum = x or triplet of -1, -1, -1
 */
fun findTripletWithSumInSortedArray(arr: IntArray, x: Int): Triple<Int, Int, Int> {
    for (i in arr.indices) {
        val pair = findPairSum(arr, x - arr[i], i + 1, arr.lastIndex)
        if (pair.first != -1) {
            return Triple(i, pair.first, pair.second)
        }
    }
    return Triple(-1, -1, -1)
}

private fun findPairSum(arr: IntArray, x: Int, from: Int, to: Int): Pair<Int, Int> {
    var left = from
    var right = to
    while (left < right) {
        val sum = arr[left] + arr[right]
        if (sum == x) {
            return left to right
        }
        if (sum > x) {
            --right
        } else {
            ++left
        }
    }
    return -1 to -1
}

/**
 * Given two sorted arrays, find the median of them.
 *
 * Partition the arrays into two parts, so that the number of elements in first parts of
 * the arrays is equal to the number of elements in the second parts of the arrays.
 * To do this we use binary search for the lesser array to find the middle point,
 * and use formula (first.size + second.size + 1) /  2 - firstMiddle to find
 * partition point for the larger array. If the number of elements in both
 * arrays is even, then we have equal number in both parts, else the left part
 * will be larger by one element.
 * Our goal is to partition the arrays so that all the elements in left parts are smaller
 * than all the elements in the right parts.
 *
 * To do this we:
 * a) Check if the last element in the left part of the first array is smaller than
 * the first element in the right part of the second array and the last element in
 * the first part of the second array is smaller than the first element in the
 * right part of the first array. If that is true, then we can pick our median:
 * if the size of combined arrays is odd, then we pick the maximum element between
 * the last element in the left part of the first array and the last element in
 * the left part of the second array. If the size of combined arrays is even,
 * then we calculate median according to the following logic:
 *  1. pick maximum from: max element from the left part of the first array and
 *                        max element from the left part of the second array
 *  2. pick minimum from: min element from the right part of the first array
 *                        min element from the right part of the second array
 *  3. sum the results from steps 1 and 2 and divide by 2.
 *
 *  In case the check a) is false we follow the logic
 *  1. if the max element from the left part of the first array is greater
 *     than the min element from the right part of the second array, then
 *     we continue binary search in the left part of the first array.
 *  2. if the max element from the left part of the second array is greater than
 *     the min element from the right part of the first array then we continue
 *     binary search in the right part of the first array.
 *
 *  We also need to take into account corner cases when we reach either the start or the end of
 *  any of the arrays.
 */
fun findMedianOfTwoSortedArrays(first: IntArray, second: IntArray): Double {
    return if (first.size <= second.size) {
        findMedianHelper(first, second)
    } else {
        findMedianHelper(second, first)
    }
}

private fun findMedianHelper(first: IntArray, second: IntArray): Double {
    val n1 = first.size
    val n2 = second.size
    var start = 0
    var end = n1

    while (start <= end) {
        val partitionFirst = start + (end - start) / 2
        val partitionSecond = ((n1 + n2 + 1) / 2) - partitionFirst

        val minFirst = if (partitionFirst == n1) Int.MAX_VALUE else first[partitionFirst]
        val maxFirst = if (partitionFirst == 0) Int.MIN_VALUE else first[partitionFirst - 1]
        val minSecond = if (partitionSecond == n2) Int.MAX_VALUE else second[partitionSecond]
        val maxSecond = if (partitionSecond == 0) Int.MIN_VALUE else second[partitionSecond - 1]
        if (maxFirst <= minSecond && maxSecond <= minFirst) {
            if (!odd(first, second)) {
                return (max(maxFirst, maxSecond) + min(minFirst, minSecond)) / 2.0
            } else {
                return max(maxFirst, maxSecond).toDouble()
            }
        } else if (maxFirst > minSecond) {
            end = partitionFirst - 1
        } else {
            start = partitionFirst + 1
        }
    }
    // unreachable
    throw IllegalStateException("There's always a median in two sorted arrays!")
}

private fun odd(first: IntArray, second: IntArray): Boolean {
    return (first.size + second.size) and 1 != 0
}

/**
 * Function to compare doubles.
 */
fun Double.equalsDelta(other: Double) =
    abs(this - other) < max(Math.ulp(this), Math.ulp(other)) * 2

/**
 * Given an array of integers in range from 0 until size of the array - 1,
 * find the repeating element in the array.
 *
 * Assume that only one element is repeating.
 *
 * The solution is based on the idea of traversing the array elements as
 * a linked list where each element points to the next node.
 *
 * We divide the algorithm in two phases:
 * 1. We use two pointers: slow and fast.
 * The slow pointer goes one step at a time, while the fast pointer goes two steps
 * at a time. We know that the array contains a cycle, because one element is repeating.
 * Therefore, fast pointer will enter the cycle faster than the slow pointer (if the cycle
 * doesn't start at the beginning of the array). Fast pointer may be cycling in the cycle
 * or simply going to the end of the cycle at double speed, and it will eventually meet
 * the slow pointer. At this point we move the slow pointer to the beginning of the array
 * and start phase 2.
 * 2. We move both pointers one step at a time and they will meet at the begining of the
 * cycle, our repeating element.
 *
 * E.g. [1, 3, 2, 4, 6, 5, 7, 3]
 *       0  1  2  3  4  5  6  7
 * Linked list traversal (by indices): 0 -> 1 -> 3 -> 4 -> 6 -> 7 -> 3 (cycle detected!)
 *
 * slow   fast   arr[slow] arr[arr[fast]]
 *  1      1        3         4
 *  3      4        4         7
 *  4      7        6         4
 *  6      4        7         7
 *  7      7
 *
 *  Move slow to index 0
 *  slow   fast   arr[slow] arr[arr[fast]]
 *   1      7        3           3
 *   3      3
 *
 *   Detected repeating element!
 *
 * But to account for zeroes in the array we need to always add 1 to slow and fast.
 * And we also need to subtract 1 from the result (either final slow or fast).
 */
fun findRepeatingElement(arr: IntArray): Int {
    var slow = arr[0] + 1
    var fast = arr[0] + 1
    do {
        slow = arr[slow] + 1
        fast = arr[arr[fast] + 1] + 1
    } while (slow != fast)
    slow = arr[0] + 1
    while (slow != fast) {
        slow = arr[slow] + 1
        fast = arr[fast] + 1
    }
    return slow - 1
}

/**
 * Given an array of N integers, representing an array of N books with
 * number of pages in each book represented as the element in the array and
 * k number of students who have to read all the books, minimize the maximum number
 * of pages a student has to read, taking into account the following rule:
 * - a student can read only contiguous books, i.e. a student can read books
 * number 0, 1, 2 ets, but he can't read books 0, 2, 5
 *
 * E.g. 1.
 * books:    [10, 20, 30, 40]
 * students: 2
 * Optimal division: student 1 -> 10, 20, 30
 *                   student 2 -> 40
 * I.e. the maximum number of pages a student has to
 * read with this division of books is 60.
 *
 * E.g. 2.
 * books:    [10, 20, 30]
 * students: 1
 * Optimal division: student 1 -> 10, 20, 30
 *
 * I.e. the maximum number of pages a student has to
 * read with this division of books is 60.
 *
 * E.g. 3.
 * books:    [10, 5, 30, 1, 2, 5, 10, 10]
 * students: 3
 * Optimal division: student 1 -> 10, 5 = 25
 *                   student 2 -> 30
 *                   student 3 -> 1, 2, 5, 10, 10 = 28
 *
 * I.e. the maximum number of pages a student has to
 * read with this division of books is 30.
 *
 */
fun allocateMinimumNumberOfPagesRecursive(books: IntArray, students: Int): Int {
    return allocatePagesRecursiveHelper(books, students, books.size)
}

private fun allocatePagesRecursiveHelper(books: IntArray, students: Int, size: Int): Int {
    // if there's only one book then all we need is to return the number of pages in it
    if (size == 1) {
        return books[0]
    }
    // if there's only one student then all we need is to return the sum of
    // all pages in the books
    if (students == 1) {
        return arrSum(books, 0, size)
    }
    var result = Int.MAX_VALUE
    for (i in 1 until size) {
        // traverse the array and make recursive calls on each iteration
        result = min( // compare the result with the recursive call and select min
            result,
            // select max between the two calculations
            max(
                // calculate the number of pages for students - 1
                allocatePagesRecursiveHelper(books, students - 1, i),
                // calculate the number of pages for current student
                arrSum(books, i, size)
            )
        )
    }
    return result
}

private fun arrSum(arr: IntArray, from: Int, to: Int): Int {
    var sum = 0
    for (i in from until to) {
        sum += arr[i]
    }
    return sum
}

/**
 * The solution is based on the idea that the answer lies in the range between
 * the maximum number of pages a single book has and the sum of all pages in all the books.
 *
 */
fun allocateMinimumNumberOfPages(books: IntArray, students: Int): Int {
    var sum = 0
    var max = Int.MIN_VALUE
    // compute the sum of all books and find the max number of pages in the books.
    for (i in books.indices) {
        sum += books[i]
        max = max(max, books[i])
    }
    var low = max
    var high = sum
    var result = 0
    while (low <= high) {
        val mid = low + (high - low) / 2
        if (isFeasible(books, students, mid)) {
            // if this number of students can divide the books among themselves to
            // read no greater than the mid number of pages, then the answer is feasible.
            // so save the result and check the left part of the range, since we want
            // to find the smallest possible answer
            result = mid
            high = mid - 1
        } else {
            // else check the right part of the range
            low = mid + 1
        }
    }
    return result
}

/**
 * Function that checks if the [books] can be divided between the [students]
 * so that each student reads less than or equal to the [answer] number of
 * pages.
 */
private fun isFeasible(books: IntArray, students: Int, answer: Int): Boolean {
    var sum = 0
    var studentsRequiredToRead = 1
    for (i in books.indices) {
        // if the previously computed sum and the number of pages in the current book
        // is greater than the answer, then start with another student (sum = books[i]) and increment
        // the number of students required to read
        if (sum + books[i] > answer) {
            sum = books[i]
            ++studentsRequiredToRead
        } else {
            sum += books[i]
        }
    }
    return studentsRequiredToRead <= students
}

/**
 * A similar task from leetcode.
 * A conveyor belt has packages that must be shipped from one port to another within [days].
 *
 * The ith package on the conveyor belt has a weight of weights[i]. Each day, we load the ship
 * with packages on the conveyor belt (in the order given by weights). We may not load more
 * weight than the maximum weight capacity of the ship.
 *
 * Return the least weight capacity of the ship that will result in all the packages on the
 * conveyor belt being shipped within [days].
 */
fun shipWithinDays(weights: IntArray, days: Int): Int {
    var sum = 0
    var max = Int.MIN_VALUE
    for (i in weights.indices) {
        sum += weights[i]
        max = Math.max(max, weights[i])
    }
    var low = max
    var high = sum
    var result = 0
    while (low <= high) {
        val middle = low + (high - low) / 2
        if (isWeightAllowed(weights, days, middle)) {
            result = middle
            high = middle - 1
        } else {
            low = middle + 1
        }
    }
    return result
}

fun isWeightAllowed(weights: IntArray, days: Int, answer: Int): Boolean {
    var sum = 0
    var daysNeeded = 1
    for (i in weights.indices) {
        if (sum + weights[i] > answer) {
            sum = weights[i]
            ++daysNeeded
        } else {
            sum += weights[i]
        }
    }
    return daysNeeded <= days
}

fun findLastOccurrenceInArraySortedDecreasing(array: IntArray, target: Int) : Int {
    var start = 0
    var end = array.lastIndex
    while (start <= end) {
        val mid = start + (end - start) / 2
        if (array[mid] == target) {
            if (mid < array.lastIndex && array[mid + 1] == target) {
                start = mid + 1
            } else {
                return mid
            }
        } else {
            if (array[mid] < target) {
                end = mid - 1
            } else {
                start = mid + 1
            }
        }
    }
    return -1
}








