package sedgewick_algorithms_c.chapter03.deviation

interface Deviation<T : Number> {
    val average: T
    val standard: T
}