package leetcode.ace_coding.stack.asteroid_collision

import kotlin.math.abs

fun asteroidCollision(asteroids: IntArray): IntArray {
    val stack = ArrayDeque<Int>()
    var i = 0
    while (i < asteroids.size) {
        if (stack.isEmpty()) {
            stack.addLast(asteroids[i++])
        } else {
            while (i < asteroids.size && stack.isNotEmpty() && stack.last() > 0 && asteroids[i] < 0) {
                val current = asteroids[i]
                val prev = stack.last()
                val diff = prev + current
                when {
                    diff > 0 -> {
                        ++i
                    }

                    diff < 0 -> {
                        stack.removeLast()
                    }

                    else -> {
                        stack.removeLast()
                        ++i
                    }
                }
            }
            if (i < asteroids.size) {
                stack.addLast(asteroids[i++])
            }
        }
    }
    return stack.toIntArray()
}
