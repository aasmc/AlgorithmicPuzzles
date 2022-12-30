package geeks_for_geeks.algorithms.greedy

/**
 * Given a list of activities, represented by an IntRange, i.e.
 * they last from IntRange.first to IntRange.last,
 * count the number of activities that can be performed on
 * a single-task machine.
 *
 * Return the activities, which can be performed.
 *
 * Algorithm:
 * 1. sort the activities by their end-times
 * 2. add first activity to the result as the one that will always be executed
 * 3. traverse other activities:
 *      - if current activity overlaps with the last picked activity, ignore it
 *      - otherwise, add it to the result
 *
 * Time Complexity O(N*LogN)
 */
fun activitySelection(activities: List<IntRange>): List<IntRange> {
    if (activities.isEmpty()) return emptyList()

    val sorted = activities.sortedBy { it.last } // N*LogN
    val result = mutableListOf<IntRange>()
    var last = sorted.first()
    result.add(last)
    for (i in 1 until sorted.size) {
        val current = sorted[i]
        if (last.last > current.first) continue // overlaps
        last = current
        result.add(current)
    }
    return result.toList()
}