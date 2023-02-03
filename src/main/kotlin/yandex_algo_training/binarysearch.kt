package yandex_algo_training

import kotlin.math.min

/**
 * Left binary search. Finds first value from left to right inclusive that is acceptable.
 * If no value is acceptable, returns [right].
 *                                 _____________________________
 * Not acceptable                 | Acceptable
 * _______________________________|
 */
fun <T> leftBinSearch(
    left: Int,
    right: Int,
    checkParams: List<T> = emptyList(),
    check: (Int, List<T>) -> Boolean
): Int {
    var l = left
    var r = right
    while (l < r) {
        val m = l + (r - l) / 2
        if (check(m, checkParams)) { // acceptable
            r = m
        } else { // not acceptable
            l = m + 1
        }
    }
    return l
}


/**
 * Right binary search. finds last acceptable value from left to right inclusive.
 *
 * ______________________________
 *   Acceptable                 \   Not acceptable.
 *                               \_________________________
 */
fun <T> rightBinSearch(
    left: Int,
    right: Int,
    checkParams: List<T>,
    check: (Int, List<T>) -> Boolean
): Int {
    var l = left
    var r = right
    while (l < r) {
        // round to upper
        val m = l + 1 + (r - l) / 2
        if (check(m, checkParams)) { // acceptable
            l = m
        } else { // not acceptable
            r = m - 1
        }
    }
    return l
}

/**
 * School committee consists of parents, teachers and pupils.
 * Number of parents must be not less than 1|3 of all committee members.
 * Currently, the number of parents is K and the number of committee members
 * is N.
 *
 * Find the minimum number of parents that must be added to the committee
 * to satisfy the condition of 1|3.
 *
 * (K + M) / (N + M) >= 1 / 3 Where M is the number of parents to be added.
 *
 * Solution via binary search. (Don't use floating point division here!)
 *
 *                    M______________________________
 *    Not enough      | Enough
 * ___________________|
 *
 * Assume:
 * Left = 0
 * Right = N (If we add N number of parent, then committee will definitely have more than
 * 1|3 number of parents. Binary search is O(logN) so this is not time-consuming, even if
 * N is 10^9).
 */

private val checkEndowment: (Int, List<Int>) -> Boolean = { candidate, params ->
    val (total, currentParents) = params
    (currentParents + candidate) * 3 >= total + candidate
}

fun findNumberOfParents(k: Int, n: Int): Int {
    return leftBinSearch(
        0,
        n,
        listOf(n, k),
        checkEndowment
    )
}


/**
 * Alex decided to prepare for interview with Yandex. He chose N assignments
 * from leetcode.com to solve. On the first day he solves K assignments.
 * On every next day, Alex solves one assignment more that on the previous day.
 *
 * How many days does Alex need to get prepared for an interview?
 *
 * Solution. We will use binary search to find the minimum number of days, needed to solve
 * not less than N assignments. We will need the arithmetic progression formula.
 *
 * k, k + 1, k + 2 .... k + m - 1
 * (k + k + m - 1) * (m / 2)
 * (2k + m - 1) * m / 2
 */

val checkProblemCount: (Int, List<Int>) -> Boolean = { candidateDays, params ->
    val (totalTasks, knownTasks) = params
    (knownTasks + (knownTasks + candidateDays - 1)) * candidateDays / 2 >= totalTasks
}

fun findNumberOfDaysForInterview(totalTasks: Int, tasksOnFirstDay: Int): Int {
    return leftBinSearch(
        0,
        totalTasks,
        listOf(totalTasks, tasksOnFirstDay),
        checkProblemCount
    )
}


/**
 * Michael teaches algorithms. He has a board with dimensions W * H cm.
 * Michael needs to place N square stickers on the board (width of the
 * square is an integer).
 *
 * Find the max width of the sticker square so as all N stickers could
 * be placed on the board.
 *
 * ---------------------
 *  Acceptable         \  Not Acceptable
 *                     \____________________________
 *
 *
 * While stickers are small, they are acceptable.
 * Use right binary search to find the max width of the sticker.
 */

val checkStickers: (Int, List<Int>) -> Boolean = { size, params ->
    val (numberOfStickers, boardWidth, boardHeight) = params
    val stickersInRow = boardWidth / size
    val stickersInCol = boardHeight / size
    stickersInRow * stickersInCol >= numberOfStickers
}

fun findStickerWidth(
    numberOfStickers: Int,
    boardWidth: Int,
    boardHeight: Int
): Int {
    return rightBinSearch(
        0,
        min(boardWidth, boardHeight),
        listOf(numberOfStickers, boardWidth, boardHeight),
        checkStickers
    )
}


/**
 * Given a finite non-decreasing sequence of integers,
 * find index of first element greater or equal to X, or else
 * return the length of the sequence.
 */

fun findFirstGreaterOrEqual(sequence: List<Int>, number: Int): Int {
    val result = leftBinSearch(
        left = 0,
        right = sequence.size - 1,
        checkParams = emptyList<Int>()
    ) { index, _ ->
        sequence[index] >= number
    }
    return if (sequence[result] < number) { // not found the element in the list
        sequence.size
    } else {
        result
    }
}

/**
 * Count the number of X in the given sorted list.
 */
fun countNumberOfX(list: List<Int>, number: Int): Int {
    var greaterOrEqual = leftBinSearch(
        0,
        list.lastIndex,
        emptyList<Int>()
    ) { index, _ ->
        list[index] >= number
    }
    if (list[greaterOrEqual] < number) {
        greaterOrEqual = list.size
    }

    var greater = leftBinSearch(
        0,
        list.lastIndex,
        emptyList<Int>()
    ) { index, _ ->
        list[index] > number
    }
    if (list[greater] <= number) {
        greater = list.size
    }
    return greater - greaterOrEqual
}























