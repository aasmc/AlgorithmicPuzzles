package leetcode.ace_coding.queue.num_recent_calls

class RecentCounter() {

    fun ping(t: Int): Int {
        pings.addLast(t)
        while (pings.first() < t - delta) {
            pings.removeFirst()
        }
        return pings.size
    }

    private val pings = ArrayDeque<Int>()
    private val delta = 3000

}