package leetcode.medium.yandex_prep.maximize_distance_to_closest

class MaximizeDistanceSolution {

    fun maxDistToClosest(seats: IntArray): Int {
        var result = 0
        var prevOccupiedIdx = -1
        for (idx in seats.indices) {
            if (seats[idx] == 1) {
                result = if (prevOccupiedIdx == -1) {
                    idx
                } else {
                    maxOf(result, (idx - prevOccupiedIdx) / 2)
                }
                prevOccupiedIdx = idx
            }
        }
        result = maxOf(result, (seats.lastIndex - prevOccupiedIdx))
        return result
    }

    fun maxDistToClosest2(seats: IntArray): Int {
        val fromLeft = IntArray(seats.size)
        var count = 0
        var startZero = seats[0] == 0
        for (i in fromLeft.indices) {
            if (seats[i] != 0) {
                startZero = false
                count = 0
            }
            if (startZero) {
                fromLeft[i] = Int.MAX_VALUE
            } else if (seats[i] == 0) {
                fromLeft[i] = ++count
            }
        }
        val fromRight = IntArray(seats.size)
        startZero = seats[seats.lastIndex] == 0
        count = 0
        for (i in fromRight.lastIndex downTo 0) {
            if (seats[i] != 0) {
                startZero = false
                count = 0
            }
            if (startZero) {
                fromRight[i] = Int.MAX_VALUE
            } else if (seats[i] == 0) {
                fromRight[i] = ++count
            }
        }

        var res = Int.MIN_VALUE
        for (i in seats.indices) {
            res = maxOf(res, minOf(fromLeft[i], fromRight[i]))
        }
        return res
    }

}