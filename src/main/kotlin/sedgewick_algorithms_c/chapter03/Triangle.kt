package sedgewick_algorithms_c.chapter03

data class Triangle(
    val a: Point2D,
    val b: Point2D,
    val c: Point2D
) {
    fun area(): Double {
        // S=1/2((х1-х3)(у2-у3)-(х2-х3)(у1-у3))
        return ((a.x - c.x) * (b.y - c.y) - (b.x - c.x) * (a.y - c.y)) / 2
    }
}
