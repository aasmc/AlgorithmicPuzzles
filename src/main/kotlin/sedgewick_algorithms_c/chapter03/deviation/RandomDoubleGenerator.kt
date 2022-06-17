package sedgewick_algorithms_c.chapter03.deviation

import kotlin.random.Random

class RandomDoubleGenerator : RandomGenerator<Double> {
    override fun generateRandomNumber(): Double {
        return Random.nextDouble(1000.0)
    }
}