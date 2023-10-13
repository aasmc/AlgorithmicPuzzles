package leetcode.ace_coding.stack.asteroid_collision

import kotlin.math.abs

fun asteroidCollision(asteroids: IntArray): IntArray {
    val stack = ArrayDeque<Int>()
    for (i in asteroids.indices) {
        if (stack.isEmpty()) {
            stack.addLast(asteroids[i])
        } else {
            val current = asteroids[i]
            val prev = stack.last()
            // case 1: moving in the same direction
            if ((current > 0 && prev > 0) || (current < 0 && prev < 0)) {
                stack.addLast(current)
            } else {
                val absCurrent = abs(current)
                val absPrev = abs(prev)
                // case 2: moving towards each other and prev == current -> both explode
                if (absCurrent == absPrev && prev > 0 && current < 0) {
                    stack.removeLast()
                    // case 3: moving towards each other and prev < current -> prev explodes
                    // current moves further and collides with the next asteroid
                } else if (absCurrent > absPrev && prev > 0 && current < 0) {
                    stack.removeLast()
                    if (lastWins(stack, current, absCurrent)) {
                        stack.addLast(current)
                    }
                    // case4: moving away from each other
                } else if (prev < 0 && current > 0) {
                    stack.addLast(current)
                }
            }
        }
    }
    val result = IntArray(stack.size)
    for (i in stack.indices) {
        result[i] = stack[i]
    }
    return result
}

private fun lastWins(stack: ArrayDeque<Int>, last: Int, lastAbs: Int): Boolean {
    while (stack.isNotEmpty()) {
        if (stack.last() > 0 && last > 0 || stack.last() < 0 && last < 0) {
            return true
        } else {
            if (abs(stack.last()) == lastAbs) {
                stack.removeLast()
                return false
            } else
                if (abs(stack.last()) < lastAbs && stack.last() > 0 && last < 0) {
                    stack.removeLast()
                } else {
                    return false
                }
        }
    }
    return true
}