package yandex_algo_training

import kotlin.math.max

/**
 * N people have visited website. In-i is the time a person-i entered the website,
 * Out-i is the time a person-i left the website. A person is considered to be
 * present on the website from In-i to Out-i inclusive.
 *
 * Find out the max number of people present on the website at one time.
 */
data class Event<out T>(
    val data: T,
    val type: Int
)

private const val EXIT_TYPE = 1
private const val ENTER_TYPE = -1
private const val BOSS_ENTER = 0

// n - number of people
// tin - arrival time
// tout - departure time
fun maxVisitorsOnline(n: Int, tin: IntArray, tout: IntArray): Int {
    val events = getSortedEvents(n, tin, tout)
    var online = 0
    var maxOnline = 0
    for (event in events) {
        if (event.type == ENTER_TYPE) {
            online += 1
        } else {
            online -= 1
        }
        maxOnline = max(online, maxOnline)
    }
    return maxOnline
}

private fun getSortedEvents(n: Int, tin: IntArray, tout: IntArray): List<Event<Int>> {
    val events = mutableListOf<Event<Int>>()
    repeat(n) { idx ->
        events.add(Event(data = tin[idx], type = ENTER_TYPE)) // arrival event
        events.add(Event(data = tout[idx], type = EXIT_TYPE)) // departure event
    }
    events.sortWith(compareBy<Event<Int>> { it.data }.then(compareBy { it.type }))
    return events.toList()
}

/**
 * Find out the total time when at least one person was present on the website.
 * If we arrive to an event with the counter != 0, it means that there was someone
 * on the website before, so we add to the result the diff between current and previous event.
 */
fun totalTimePresent(n: Int, tin: IntArray, tout: IntArray): Int {
    val sortedEvents = getSortedEvents(n, tin, tout)
    var online = 0
    var notEmptyTime = 0
    for (i in sortedEvents.indices) {
        if (online > 0) {
            notEmptyTime += sortedEvents[i].data - sortedEvents[i - 1].data
        }
        if (sortedEvents[i].type == ENTER_TYPE) {
            ++online
        } else {
            --online
        }
    }
    return notEmptyTime
}

/**
 * N people have visited website. In-i is the time a person-i entered the website,
 * Out-i is the time a person-i left the website. A person is considered to be
 * present on the website from In-i to Out-i inclusive. Boss entered the
 * website M times at the moments Boss-i and tracked the number of people online.
 * Events when boss entered the website are sorted in ascending order.
 *
 * Find out the amount of people online in times when Boss entered the website.
 *
 * It is considered that if one person enters the website at the same time when another person
 * leaves the website and at the same time the boss enters the website, the flow of events
 * is the following:
 * enter person -> enter boss -> leave person
 *
 * @param n - total number of visitors except boss
 * @param tin array with arrival times
 * @param tout array with departure times
 * @param m the number of times boss visited the website
 * @param boss array with arrival times of the boss
 */
fun bossOnlineTracker(n: Int, tin: IntArray, tout: IntArray, m: Int, boss: IntArray): List<Int> {
    val events = mutableListOf<Event<Int>>()
    repeat(n) { idx ->
        events.add(Event(tin[idx], ENTER_TYPE))
        events.add(Event(tout[idx], EXIT_TYPE))
    }
    repeat(m) { idx ->
        events.add(Event(boss[idx], BOSS_ENTER))
    }
    events.sortWith(compareBy<Event<Int>> { it.data }.then(compareBy { it.type }))
    var online = 0
    val bossAnswer = mutableListOf<Int>()
    for (i in events.indices) {
        when (events[i].type) {
            ENTER_TYPE -> {
                ++online
            }
            EXIT_TYPE -> {
                --online
            }
            else -> {
                bossAnswer.add(online)
            }
        }
    }
    return bossAnswer.toList()
}
