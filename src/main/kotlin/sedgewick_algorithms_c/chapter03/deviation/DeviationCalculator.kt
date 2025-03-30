package sedgewick_algorithms_c.chapter03.deviation

interface DeviationCalculator<T : Number> {
    fun calculate(): Deviation<T>
}