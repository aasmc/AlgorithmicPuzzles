package leetcode.top_interview_150.array_string.gas_stations

class GasStationsSolution {

    fun canCompleteCircuit(gas: IntArray, cost: IntArray): Int {
        if (gas.sumOf { it } < cost.sumOf { it }) {
            return -1
        }
        var total = 0
        var start = 0
        for (idx in gas.indices) {
            val diff = gas[idx] - cost[idx]
            total += diff
            if (total < 0) {
                total = 0
                start = idx + 1
            }
        }
        return start
    }


}