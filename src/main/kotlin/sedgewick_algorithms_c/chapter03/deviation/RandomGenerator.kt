package sedgewick_algorithms_c.chapter03.deviation

interface RandomGenerator<T : Number> {
    fun generateRandomNumber() : T
}