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
        if (last.last >= current.first) continue // overlaps
        last = current
        result.add(current)
    }
    return result.toList()
}

data class KnapsackItem(
    val value: Double,
    val weight: Double
)

/**
 * Given a capacity of a knapsack and a list of items, each of which has a value
 * and a weight, we need to find out the maximum value of items which can be
 * put into the knapsack. If a complete item cannot be put into the knapsack,
 * we can put a fraction of it.
 *
 * Algorithm:
 * 1. sort all items in decreasing order of their value / weight ratio
 * 2. initialize result to 0 and currentCapacity = capacity
 * 3. for every item in the sorted list:
 *    - if item can be completely put into the knapsack:
 *       a. currentCapacity -= item weight
 *       b. result += item value
 *    - else result += (item value) * (currentCapacity / item weight)
 *           return result
 * 4. return result
 */
fun fractionalKnapsack(capacity: Double, items: List<KnapsackItem>): Double {
    var result = 0.0
    var currentCapacity = capacity
    val sorted = items.sortedByDescending { it.value / it.weight }
    for (itm in sorted) {
        if (itm.weight <= currentCapacity) {
            currentCapacity -= itm.weight
            result += itm.value
        } else {
            result += itm.value * (currentCapacity / itm.weight)
            break
        }
    }
    return result
}

data class JobSequence(
    val deadLine: Int,
    val profit: Double
)

/**
 * Given a list of jobs (deadlines with associated profits),
 * find max profit taking into account the following conditions:
 * 1. you get full profit only if the job is completed by deadline
 * 2. every job takes one unit of time
 * 3. only one job can be assigned at a time
 * 4. time starts with 0
 */
fun jobSequencing(jobs: List<JobSequence>): Double {
    val sorted = jobs
        .sortedByDescending { it.profit }
    val maxDeadline = jobs.maxBy { it.deadLine }.deadLine
    val availableSlots = Array<Double?>(maxDeadline) { null }
    for (job in sorted) {
        var deadline = job.deadLine - 1
        while (deadline >= 0 && availableSlots[deadline] != null) {
            deadline--
        }
        if (deadline < 0) continue
        availableSlots[deadline] = job.profit
    }
    return availableSlots.filterNotNull().sumOf { it }
}
































