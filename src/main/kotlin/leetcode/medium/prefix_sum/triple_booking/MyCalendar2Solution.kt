package leetcode.medium.prefix_sum.triple_booking

import java.util.TreeMap

class MyCalendar2Solution {

    class MyCalendarTwo() {

        private val map = TreeMap<Int, Int>()

        fun book(start: Int, end: Int): Boolean {
            map.merge(start, 1, Int::plus) // increase count when event starts
            map.merge(end, -1, Int::plus) // decrease count when event ends
            var count = 0
            var canAdd = true
            for(value in map.values) {
                count += value
                if (count == 3) {
                    canAdd = false
                    break
                }
            }
            if (!canAdd) {
                // rollback
                map.merge(start, -1, Int::plus)
                map.merge(end, 1, Int::plus)
            }
            return canAdd
        }


    }

}