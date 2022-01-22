package advent_of_code.year_2020.day7

import java.io.File

fun main() {
    val tree = buildBagTreeTwo()
    println(tree.getChildrenCountBFS(SHINY_GOLD))
}

val digits = "\\d+".toRegex()

private fun Map<Color, Rule>.getChildrenCountBFS(color: Color): Int {
    val children = getOrDefault(color, setOf())
    if (children.isEmpty()) return 0
    var total = 0
    for(child in children) {
        // findAll Returns a sequence of all occurrences of a regular expression (Sequence<MatchResult>)
        // within the input string, beginning at the specified startIndex, which is 0 by default.
        // value is the substring from the input string captured by this match.
        val count = digits.findAll(child).first().value.toInt()
        // replace -> replaces all occurrences of this regular expression in the specified [input]
        // string with specified [replacement] expression.
        val bag = digits.replace(child, "").trim()
        // we add count to the total and multiply count by the number of bags inside each bag
        total += count + count * getChildrenCountBFS(bag)
    }
    return total
}

/**
 * Builds a map of color of the container bag TO the set of specifications of included bags.
 * The set of specifications is represented as the Set<String>.
 */
fun buildBagTreeTwo(): Map<Color, Rule> {
    val rules = hashMapOf<Color, Rule>()
    File("src/main/kotlin/advent_of_code/day7/input.txt")
        .forEachLine { line ->
            val (parent, allChildren) = line
                .replace(Regex("bags?\\.?"), "")
                .split("contain")
                .map { it.trim() }
            val rule: Set<String> =
                if (allChildren.contains("no other")) {
                    emptySet()
                } else {
                    allChildren.split(',').map { it.trim() }.toSet()
                }
            rules[parent] = rule
        }
    return rules
}