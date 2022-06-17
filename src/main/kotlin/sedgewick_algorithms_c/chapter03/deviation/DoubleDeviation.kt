package sedgewick_algorithms_c.chapter03.deviation

data class DoubleDeviation(
    override val average: Double,
    override val standard: Double
) : Deviation<Double> {
    override fun toString(): String {
        return "Average deviation: $average\nStandard deviation: $standard"
    }
}
