package sedgewick_algorithms_c.chapter03

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class Point2DTest {
    @Test
    fun testDistancePositive() {
        val first = Point2D(x = 0.0, y = 3.0)
        val second = Point2D(x = 0.0, y = 6.0)
        val distance = first.distanceTo(second)
        assertEquals(3.0, distance)
    }

    @Test
    fun areThreePointsOnTheSameLine_Positive() {
        val first = Point2D(x = 0.0, y = 10.0)
        val second = Point2D(x = 1.0, y = 10.0)
        val third = Point2D(x = 2.0, y = 10.0)
        assertTrue(first.areThreePointsOnTheSameLine(second, third))
    }

    @Test
    fun areThreePointsOnTheSameLine_Negative() {
        val first = Point2D(x = 5.0, y = 4.0)
        val second = Point2D(x = 1.0, y = 8.0)
        val third = Point2D(x = 2.0, y = 10.0)
        assertFalse(first.areThreePointsOnTheSameLine(second, third))
    }

}