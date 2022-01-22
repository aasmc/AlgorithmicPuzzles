package advent_of_code.year_2020.day4


class Passport(private val map: Map<String, String>) {

    companion object {
        fun fromString(s: String): Passport {
            val fieldsWithValues = s.split(" ", "\n", "\r\n")
            val map = fieldsWithValues.associate {
                val (key, value) = it.split(":")
                key to value
            }
            return Passport(map)
        }
    }

    private val requiredFields = listOf(
        "byr",
        "iyr",
        "eyr",
        "hgt",
        "hcl",
        "ecl",
        "pid"
    )


    fun hasAllFieldsValid(): Boolean {
        val validEyeColors = setOf("amb", "blu", "brn", "gry", "grn", "hzl", "oth")
        val eyeColorPattern = """#[0-9a-f]{6}""".toRegex()
        return map.all { (key, value) ->
            when (key) {
                "byr" -> value.length == 4 && value.toIntOrNull() in 1920..2002
                "iyr" -> value.length == 4 && value.toIntOrNull() in 2010..2020
                "eyr" -> value.length == 4 && value.toIntOrNull() in 2020..2030
                "hgt" -> {
                    when (value.takeLast(2)) {
                        "in" -> value.removeSuffix("in").toIntOrNull() in 59..76
                        "cm" -> value.removeSuffix("cm").toIntOrNull() in 150..193
                        else -> false
                    }
                }
                "hcl" -> value matches eyeColorPattern
                "ecl" -> value in validEyeColors
                "pid" -> value.length == 9 && value.all(Char::isDigit)
                else -> true
            }
        }
    }

    fun hasAllRequiredFields(): Boolean =
        map.keys.containsAll(requiredFields)
}



























