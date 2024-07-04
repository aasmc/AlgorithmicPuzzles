package leetcode.easy.yandex_prep.number_of_recent_calls

class RecentCounter() {

    private val WINDOW = 3000
    private val counts = ArrayDeque<Int>()

    fun ping(t: Int): Int {
        counts.addLast(t)
        while (t - WINDOW > counts.first()) {
            counts.removeFirst()
        }
        return counts.size
    }

}