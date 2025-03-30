package sedgewick_algorithms_c.chapter03.deviation

import kotlin.math.sqrt

class DoubleBasedDeviationCalculator(
    private val randomGenerator: RandomGenerator<Double>,
    private val limit: Int
) : DeviationCalculator<Double> {

    override fun calculate(): DoubleDeviation {
        var m1 = 0.0
        var m2 = 0.0
        for (i in 0 until limit) {
            val rand = randomGenerator.generateRandomNumber()
            m1 += rand / limit
            m2 += rand * rand / limit
        }
        return DoubleDeviation(average = m1, standard = sqrt(m2 - m1 * m1))
    }
}