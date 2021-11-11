package advent_of_code.day10

data class JoltageAdapter(val outputJoltage: Int) {
    fun possibleInputRange(): IntRange {
        return (outputJoltage - 3)..outputJoltage
    }
    companion object {
        fun from(str: String): JoltageAdapter {
            val joltage = str.toIntOrNull()
            joltage?.let {
                return JoltageAdapter(it)
            }
            throw Exception("Incorrect input: $str")
        }
    }
}
