package advent_of_code.day9

import advent_of_code.readLinesFromFile

fun main() {
    val numbers = readLinesFromFile("day9")
        .map { it.toLong() }

    val invalidNumber = numbers.findInvalidNumberV4() ?: error("All numbers are valid!")
    println(invalidNumber)

    val sublist = numbers.findSublistOfSumV4(targetSum = invalidNumber)
        ?: error("No sublist is found")

    println(sublist.minOf { it } + sublist.maxOf { it })
}

fun List<Long>.hasPairOfSum(sum: Long): Boolean {
    forEachIndexed { i, first ->
        forEachIndexed { j, second ->
            if (i != j && (first + second) == sum) return true
        }
    }
    return false
}

fun List<Long>.hasPairOfSumUsingAny(sum: Long): Boolean =
    indices.any { i ->
        indices.any { j ->
            i != j && this[i] + this[j] == sum
        }
    }

const val GROUP_SIZE = 25

fun List<Long>.findInvalidNumberV1(): Long? {
    // traverse the list starting from the first element of the second
    // group
    for (index in (GROUP_SIZE + 1)..lastIndex) {
        // get the list of size GROUP_SIZE of preceding numbers
        // sublist doesn't create a new list. It only creates a view into the current list.
        // Hence, no memory overhead
        val prevGroup = subList(index - GROUP_SIZE, index)
        if (!prevGroup.hasPairOfSum(sum = this[index])) {
            return this[index]
        }
    }
    return null
}

fun List<Long>.findInvalidNumberV2(): Long? =
    ((GROUP_SIZE + 1)..lastIndex)
        .firstOrNull { index ->
            val prevGroup = subList(index - GROUP_SIZE, index)
            !prevGroup.hasPairOfSum(sum = this[index])
        }?.let {
            this[it]
        }


fun List<Long>.findInvalidNumberV3(): Long? =
    // windowed creates new intermediate sublists, so it impacts performance
    windowed(GROUP_SIZE + 1)
        .firstOrNull { window ->
            val prevGroup = window.dropLast(1)
            !prevGroup.hasPairOfSum(sum = window.last())
        }?.last()

fun List<Long>.findInvalidNumberV4(): Long? =
    asSequence()
        .windowed(GROUP_SIZE + 1)
        .firstOrNull { window ->
            val prevGroup = window.dropLast(1)
            !prevGroup.hasPairOfSum(sum = window.last())
        }?.last()


fun List<Long>.findSublistOfSumV1(targetSum: Long): List<Long>? {
    for (fromIndex in indices) {
        for (toIndex in (fromIndex + 1..size)) {
            val sublist = subList(fromIndex, toIndex)
            if (sublist.sum() == targetSum) {
                return sublist
            }
        }
    }
    return null
}

fun List<Long>.fundSublistOfSumV2(targetSum: Long): List<Long>? =
    indices.firstNotNullOfOrNull { fromIndex ->
        (fromIndex + 1..size)
            .firstNotNullOfOrNull { toIndex ->
                subList(fromIndex, toIndex).takeIf { it.sum() == targetSum }
            }
    }

fun List<Long>.findSublistOfSumV3(targetSum: Long): List<Long>? =
    (2..size).firstNotNullOfOrNull { sublistSize ->
        asSequence()
            .windowed(sublistSize)
            .firstOrNull { sublist ->
                sublist.sum() == targetSum
            }
    }

fun List<Long>.findSublistOfSumV4(targetSum: Long): List<Long>? {
    val prefixSum = scan(0L) { acc, elem -> acc + elem }
    return indices.firstNotNullOfOrNull { fromIndex ->
        ((fromIndex + 1)..size).firstNotNullOfOrNull { toIndex ->
            val sublistSum = prefixSum[toIndex] - prefixSum[fromIndex]
            if (sublistSum == targetSum) subList(fromIndex, toIndex) else null
        }
    }
}















