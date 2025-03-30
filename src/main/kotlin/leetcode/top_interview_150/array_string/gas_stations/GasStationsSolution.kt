package leetcode.top_interview_150.array_string.gas_stations

class GasStationsSolution {

    fun canCompleteCircuit(gas: IntArray, cost: IntArray): Int {
        // holds total amount of gas spent during the trip
        var total = 0
        // holds the amount of gas spent during the trip when we can really move, i.e.
        // we have enough gas to spend during the next move to the gas station
        var sum = 0
        // keeps track of the position, where we started to move without going into
        // negative gas tank
        var position = 0
        // try every gas station
        for (gasStation in gas.indices) {
            // sum up how much gas we need to spend to get to the next station
            // taking into account the amount of gas we can get at this gas station
            sum += gas[gasStation] - cost[gasStation]
            // if we can't reach the next station
            if (sum < 0) {
                // first accumulate the total gas spendings
                total += sum
                // reset the sum and try from the next position
                sum = 0
                position = gasStation + 1
            }
        }
        total += sum
        // total >= 0 means we could travel around the circle from that position
        // without going into negative gas tank
        return if (total >= 0) position else -1
    }


}