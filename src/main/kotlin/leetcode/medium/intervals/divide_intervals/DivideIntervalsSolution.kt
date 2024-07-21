package leetcode.medium.intervals.divide_intervals

class DivideIntervalsSolution {

    fun minGroups(intervals: Array<IntArray>): Int {
        val startTimes = IntArray(intervals.size)
        val endTimes = IntArray(startTimes.size)

        var idx = 0
        intervals.forEach { interval ->
            startTimes[idx++] = interval[0]
        }
        idx = 0
        intervals.forEach { interval ->
            endTimes[idx++] = interval[1]
        }

        startTimes.sort()
        endTimes.sort()

        var sIdx = 0
        var eIdx = 0
        var count = 0
        var res = 0
        while (sIdx < startTimes.size) {
            val start = startTimes[sIdx]
            val end = endTimes[eIdx]
            if (start <= end) {
                ++count
                ++sIdx
            } else {
                --count
                ++eIdx
            }
            res = maxOf(res, count)
        }
        return res
    }

    fun minGroups2(intervals: Array<IntArray>): Int {
        val map = hashMapOf<Int, Int>()
        intervals.forEach { (start, end) ->
            map.merge(start, 1, Int::plus)
            map.merge(end + 1, -1, Int::plus)
        }
        var res = 0
        var current = 0
        for(key in map.keys.sorted()) {
            current += map[key]!!
            res = maxOf(res, current)
        }
        return res
    }

}