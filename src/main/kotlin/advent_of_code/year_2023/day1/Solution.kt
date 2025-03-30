package advent_of_code.year_2023.day1

import advent_of_code.readLinesFromFile


fun main() {
    val lines = readLinesFromFile("year_2023/day1", "input2.txt")
    val result = solvePartTwo(lines)
    println(result)
}

private fun solvePartTwo(lines: List<String>): Int {
    val digits = hashMapOf<String, Int>(
        "one" to 1,
        "two" to 2,
        "three" to 3,
        "four" to 4,
        "five" to 5,
        "six" to 6,
        "seven" to 7,
        "eight" to 8,
        "nine" to 9
    )
    var result = 0
    var first = ""
    var second = ""
    lines.forEach { line ->
        var i = 0
        var j = line.lastIndex
        var firstFound = false
        var lastFound = false
        while (!firstFound || !lastFound) {
            if (line[i].isDigit() && !firstFound) {
                first += line[i]
                firstFound = true
            } else if (!firstFound) {
                for(key in digits.keys) {
                    if (line.substring(i).startsWith(key)) {
                        first = digits[key].toString()
                        firstFound = true
                        break
                    }
                }
                if (!firstFound) {
                    ++i
                }
            }

            if (line[j].isDigit() && !lastFound) {
                second += line[j]
                lastFound = true
            } else if (!lastFound) {
                for(key in digits.keys) {
                    if (line.substring(j).startsWith(key)) {
                        second = digits[key].toString()
                        lastFound = true
                        break
                    }
                }
                if (!lastFound) {
                    --j
                }
            }
        }
        first += second
        result += first.toInt()
        first = ""
        second = ""
    }
    return result
}

private fun solve(lines: List<String>): Int {
    var result = 0
    val firstBuilder = StringBuilder()
    val secondBuilder = StringBuilder()
    lines.forEach { line ->
        var i = 0
        var j = line.lastIndex
        var firstFound = false
        var lastFound = false
        while (!firstFound || !lastFound) {
            if (line[i].isDigit() && !firstFound) {
                firstBuilder.append(line[i++])
                firstFound = true
            } else {
                i++
            }
            if (line[j].isDigit() && !lastFound) {
                secondBuilder.append(line[j--])
                lastFound = true
            } else {
                j--
            }

        }
        firstBuilder.append(secondBuilder.toString())
        result += firstBuilder.toString().toInt()
        firstBuilder.clear()
        secondBuilder.clear()
    }
    return result
}

