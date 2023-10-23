package leetcode.ace_coding.binary_search.koko_bananas

class KokoBananasSolution {

    fun minEatingSpeed(piles: IntArray, h: Int): Int {
        val max = piles.maxOf { it }
        return binSearch(1, max, piles) { p, mid ->
            var cnt = 0
            for (pile in p) {
                if (pile < mid) {
                    cnt++
                } else if (mid != 0) {
                    cnt += pile / mid
                    if (pile % mid != 0) {
                        cnt++
                    }
                }
            }
            cnt <= h
        }
    }

    private inline fun binSearch(
        s: Int,
        e: Int,
        params: IntArray,
        check: (IntArray, Int) -> Boolean
    ): Int {
        var start = s
        var end = e
        while (start < end) {
            val mid = start + (end - start) / 2
            if (check(params, mid)) {
                end = mid
            } else {
                start = mid + 1
            }
        }
        return start
    }

}