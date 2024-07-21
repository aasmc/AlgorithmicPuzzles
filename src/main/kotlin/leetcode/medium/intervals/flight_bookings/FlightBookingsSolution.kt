package leetcode.medium.intervals.flight_bookings

class FlightBookingsSolution {

    fun corpFlightBookings(bookings: Array<IntArray>, n: Int): IntArray {
        val result = IntArray(n)
        bookings.forEach { (from, to, seats) ->
            result[from - 1] += seats
            if (to < n) {
                result[to] -= seats
            }
        }
        for (i in 1 until result.size) {
            result[i] += result[i - 1]
        }
        return result
    }

}