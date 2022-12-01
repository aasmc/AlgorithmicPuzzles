package advent_of_code.year_2022.day_01

import advent_of_code.readLinesFromFile
import java.util.PriorityQueue

fun main() {
    val allCalories = readLinesFromFile("year_2022/day_01")
    println(findMaxCalories(allCalories))
    println(findTopThreeCaloriesEasy(allCalories))
}

fun findMaxCalories(allCalories: List<String>): Int {
    var max = Int.MIN_VALUE
    var current = 0
    allCalories.forEach { line ->
        if (line.isNotBlank()) {
            current += line.toInt()
        } else {
            max = maxOf(max, current)
            current = 0
        }
    }
    return max
}

fun findTopThreeCaloriesPriorityQueue(allCalories: List<String>): Long {
    val pq = PriorityQueue<Int>(Comparator.reverseOrder())
    var current = 0
    allCalories.forEach { line ->
        if (line.isNotBlank()) {
            current += line.toInt()
        } else {
            pq.add(current)
            current = 0
        }
    }
    return pq.poll().toLong() + pq.poll().toLong() + pq.poll().toLong()
}

fun findTopThreeCaloriesEasy(allCalories: List<String>): Long {
    var max = Int.MIN_VALUE
    var secondMax = max + 1
    var thirdMax = max + 2
    var current = 0
    allCalories.forEach { line ->
        if (line.isNotBlank()) {
            current += line.toInt()
        } else {
            if (current > max) {
                thirdMax = secondMax
                secondMax = max
                max = current
            } else if (current > secondMax) {
                thirdMax = secondMax
                secondMax = current
            } else if (current > thirdMax) {
                thirdMax = current
            }
            current = 0
        }
    }
    return max.toLong() + secondMax.toLong() + thirdMax.toLong()
}