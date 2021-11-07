package advent_of_code.day7

import java.io.File

typealias Color = String
typealias Rule = Set<String>

const val SHINY_GOLD = "shiny gold"

fun main() {
    val rules: Map<Color, Rule> = buildBagTree()
    val containers = findContainersDFS(rules)
    println(containers)
    println()
    println(containers.size)
}

fun findContainersDFS(rules: Map<Color, Rule>): Set<Color> {
    var known = setOf(SHINY_GOLD)
    var next = setOf(SHINY_GOLD) + rules[SHINY_GOLD]!!
    while (true) {
        val toFind = next - known
        if (toFind.isEmpty()) break
        known = known + next
        next = toFind.mapNotNull { rules[it] }.flatten().toSet()
    }
    return known
}

/**
 * Builds a map of color of a bag contained in another bag TO
 * a set of colors of bags that can potentially contain our bag.
 */
fun buildBagTree(): Map<Color, Set<String>> {
    val rules = hashMapOf<Color, Rule>()
    File("src/main/kotlin/advent_of_code/day7/input.txt")
        .forEachLine { line ->
            val (parent, allChildren) = line
                .replace(Regex("\\d+"), "")// remove all digits
                .replace(Regex("bags?\\.?"), "") // remove all occurrences of: bag, bags, bag., bags.
                .split("contain")
                .map { it.trim() }
            val childrenColors = allChildren.split(',').map { it.trim() }.toSet()

            for (childColor in childrenColors) {
                rules.compute(childColor) { _, rule ->
                    if (rule == null) {
                        setOf(parent)
                    } else {
                        rule + parent
                    }
                }
            }
        }
    return rules
}







