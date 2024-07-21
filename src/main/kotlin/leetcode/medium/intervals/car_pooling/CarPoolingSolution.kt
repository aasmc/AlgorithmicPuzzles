package leetcode.medium.intervals.car_pooling

class CarPoolingSolution {

    fun carPooling(trips: Array<IntArray>, capacity: Int): Boolean {
        val tripPointToPassengerCount = hashMapOf<Int, Int>()
        trips.forEach { (pass, from, to) ->
            tripPointToPassengerCount.merge(from, pass, Int::plus)
            tripPointToPassengerCount.merge(to, -pass, Int::plus)
        }
        var count = 0
        for(tripPoint in tripPointToPassengerCount.keys.sorted()) {
            count += tripPointToPassengerCount[tripPoint]!!
            if (count > capacity) {
                return false
            }
        }
        return true
    }

}