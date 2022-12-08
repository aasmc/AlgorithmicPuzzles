package advent_of_code.year_2022.day_08

import advent_of_code.readLinesFromFile

fun main() {
    val lines = readLinesFromFile("year_2022/day_08",)
    val treeHolder = TreeHolder.buildTree(lines)
    val visibleFromEdges = treeHolder.calculateVisibleTrees()
    println(visibleFromEdges)
}

data class TreeHolder(
    val verticalTrees: List<List<Int>>,
    val horizontalTrees: List<List<Int>>,
) {

    fun calculateVisibleTrees(): Int {
        val visibilities = MutableList(horizontalTrees.size) { BooleanArray(horizontalTrees[0].size) { false } }
        for ((idx, horizontal) in horizontalTrees.withIndex()) {
            val visibility = visibilities[idx]
            markHorizontalVisible(idx, horizontal, visibility)
        }

        for ((idx, vertical) in verticalTrees.withIndex()) {
            if (idx == 0 || idx == verticalTrees.lastIndex) {
                for (visibility in visibilities) {
                    visibility[idx] = true
                }
            } else {
                markVerticalVisible(idx, vertical, visibilities)
            }
        }
        var count = 0
        for (visibility in visibilities) {
            for (isVisible in visibility) {
                if (isVisible) ++count
            }
        }
        return count
    }

    private fun markVerticalVisible(idx: Int, line: List<Int>, visibilities: List<BooleanArray>) {
        for (i in 1 until line.size - 1) {
            val visibility = visibilities[i]
            if (!visibility[idx] && checkVisibleFromEdge(i, line)) {
                visibility[idx] = true
            }
        }
    }

    private fun markHorizontalVisible(idx: Int, line: List<Int>, visible: BooleanArray) {
        assert(line.size == visible.size)
        if (idx == 0 || idx == horizontalTrees.lastIndex) {
            for (i in visible.indices) {
                visible[i] = true
            }
        } else {
            for (i in 1 until line.size - 1) {
                if (!visible[i] && checkVisibleFromEdge(i, line)) {
                    visible[i] = true
                }
            }
        }
    }

    private fun checkVisibleFromEdge(idx: Int, line: List<Int>): Boolean {
        return checkFromStart(idx, line) ||
                checkFromEnd(idx, line)
    }

    private fun checkFromStart(treeIdx: Int, line: List<Int>): Boolean {
        val tree = line[treeIdx]
        for (i in 0 until treeIdx) {
            val prevTree = line[i]
            if (prevTree >= tree) return false
        }
        return true
    }

    private fun checkFromEnd(treeIdx: Int, line: List<Int>): Boolean {
        val tree = line[treeIdx]
        for (i in treeIdx + 1 until line.size) {
            val nextTree = line[i]
            if (nextTree >= tree) return false
        }
        return true
    }

    companion object {
        fun buildTree(lines: List<String>): TreeHolder {
            val vertical = mutableListOf<MutableList<Int>>()
            val horizontal = mutableListOf<MutableList<Int>>()
            val horizontalSize = lines.first().length
            for (i in 0 until horizontalSize) {
                horizontal.add(mutableListOf())
            }
            val verticalSize = lines.size
            for (i in 0 until verticalSize) {
                vertical.add(mutableListOf())
            }
            for ((horizontalIdx, line) in lines.withIndex()) {
                for ((verticalIdx, ch) in line.withIndex()) {
                    horizontal[horizontalIdx].add(ch.digitToInt())
                    vertical[verticalIdx].add(ch.digitToInt())
                }
            }
            return TreeHolder(vertical, horizontal)
        }
    }
}