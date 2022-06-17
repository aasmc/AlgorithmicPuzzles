package sedgewick_algorithms_c.chapter03

import kotlin.math.atan2
import kotlin.math.sqrt

/**
 * Two-dimensional point on the surface.
 */
data class Point2D(
    val x: Double,
    val y: Double
) {
    /**
     * Calculates distance from this point to the [other] [Point2D]
     * on the surface.
     */
    fun distanceTo(other: Point2D): Double {
        val dx = x - other.x
        val dy = y - other.y
        return sqrt(dx * dx + dy * dy)
    }

    fun toPolarCoordinates(): PolarCoordinates {
        return PolarCoordinates(
            radius = sqrt(x * x + y + y),
            angle = atan2(y, x)
        )
    }

    fun areThreePointsOnTheSameLine(second: Point2D, third: Point2D): Boolean {
        // Find the area of the triangle and if it is equal to 0, then points are on the same line
        // S=1/2((х1-х3)(у2-у3)-(х2-х3)(у1-у3))
        val triangle = Triangle(a = this, b = second, c = third)
        val area = triangle.area()
        return area == 0.0
    }
}

/**
 * Point represented as polar coordinates: polar radius and polar angle.
 */
data class PolarCoordinates(
    val radius: Double,
    val angle: Double
)
