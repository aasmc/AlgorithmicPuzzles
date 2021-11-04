package advent_of_code.day6

import advent_of_code.readTextFromFile

val nl = System.lineSeparator()

fun main() {
    val groups = readTextFromFile("day6")
        .trim()
        .split("$nl$nl") // to split the given text by a blank line we need to use 2 new line separators

    val totalYes = getAllYesAnswers(groups)
    println(totalYes)

    val uniqueYes = getYesIntersectionSimpleWay(groups)
    val uniqueYesIdiomatic = getYesIntersectionIdiomaticWay(groups)
    println(uniqueYes)
    println(uniqueYesIdiomatic)
}

fun getYesIntersectionIdiomaticWay(groups: List<String>): Int {
    return groups.map { group ->
        group.split(nl).map(String::toSet)
    }.sumOf { answerSets ->
        answerSets.reduce { acc, next ->
            acc intersect next
        }.count()
    }
}

fun getYesIntersectionSimpleWay(groups: List<String>): Int {
    var sum = 0
    for (group in groups) {
        // answers in a group are separated by a new line character, we split by it
        val answers = group.split(nl)
        // convert an answer string to a set of characters from the string
        val answerSets: List<Set<Char>> = answers.map { it.toSet() }
        // get the intersection of characters of all groups and count the number of chars in the resulting set
        val count = answerSets.reduce { acc, set ->
            acc intersect set
        }.count()
        sum += count
    }
    return sum
}

private fun getAllYesAnswers(groups: List<String>) = groups.sumOf {
    // since every person in the group is separated by a new line character, we need to get rid of it
    it.replace(nl, "").toSet().count()
}