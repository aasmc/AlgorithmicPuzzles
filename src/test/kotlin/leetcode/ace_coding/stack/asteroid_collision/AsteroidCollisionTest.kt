package leetcode.ace_coding.stack.asteroid_collision

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class AsteroidCollisionTest {

    @Test
    fun asteroidCollisionTest() {
        val ex1 = intArrayOf(10)
        assertTrue(ex1.contentEquals(asteroidCollision(intArrayOf(10, 2, -5))))
        val ex2 = intArrayOf()
        assertTrue(ex2.contentEquals(asteroidCollision(intArrayOf(8, -8))))
        val ex3 = intArrayOf(5, 10)
        assertTrue(ex3.contentEquals(asteroidCollision(intArrayOf(5, 10, -5))))
        val ex4 = intArrayOf(-2, -1, 1, 2)
        assertTrue(ex4.contentEquals(asteroidCollision(intArrayOf(-2,-1,1,2))))
        val ex5 = intArrayOf(-2, -2, -2)
        assertTrue(ex5.contentEquals(asteroidCollision(intArrayOf(-2, -2, 1, -2))))
        val ex6 = intArrayOf(-2)
        assertTrue(ex6.contentEquals(asteroidCollision(intArrayOf(-2, 2, 1, -2))))
        val ex7 = intArrayOf(-2, -2)
        assertTrue(ex7.contentEquals(asteroidCollision(intArrayOf(1, -1, -2, -2))))

    }

}