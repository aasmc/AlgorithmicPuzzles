package sedgewick_algorithms_c.chapter03.deviation

fun main() {
    val n = 1000000
    val deviationCalculator = DoubleBasedDeviationCalculator(
        limit = n,
        randomGenerator = RandomDoubleGenerator()
    )
    val deviation = deviationCalculator.calculate()
    println(deviation)
}