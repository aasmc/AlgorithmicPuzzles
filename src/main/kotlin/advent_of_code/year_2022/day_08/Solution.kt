package advent_of_code.year_2022.day_08

import advent_of_code.readLinesFromFile

fun main() {
    val lines = readLinesFromFile("year_2022/day_08", )
    val treeHolder = TreeHolder.buildTree(lines)
    val visibleFromEdges = treeHolder.calculateVisibleTrees()
    println(visibleFromEdges)
    println(treeHolder.highestScenicScore())
}

data class TreeHolder(
    val verticalTrees: List<List<Int>>,
    val horizontalTrees: List<List<Int>>,
) {

    private fun prepareScenicScoreMatrix(): Array<IntArray> {
        val matrix = Array(horizontalTrees.size) {
            IntArray(horizontalTrees[0].size) { 1 }
        }
        return matrix
    }

    fun highestScenicScore(): Int {
        val scoreMatrix = prepareScenicScoreMatrix()
        fillMatrix(scoreMatrix)
        return getMaxScore(scoreMatrix)
    }

    private fun getMaxScore(scoreMatrix: Array<IntArray>): Int {
        var result = Int.MIN_VALUE
        scoreMatrix.forEach { row ->
            val rowMax = row.max()
            result = maxOf(result, rowMax)
        }
        return result
    }

    private fun fillMatrix(scoreMatrix: Array<IntArray>) {
        for ((horizontalIdx, line) in horizontalTrees.withIndex()) {
            if (horizontalIdx != 0 && horizontalIdx != horizontalTrees.lastIndex) {
                for ((treeIdx, _) in line.withIndex()) {
                    if (treeIdx != 0 && treeIdx != line.lastIndex) {
                        val startCount = countFromStart(treeIdx, line)
                        val endCount = countFromEnd(treeIdx, line)
                        scoreMatrix[horizontalIdx][treeIdx] *= (startCount * endCount)
                    }
                }
            }
        }
        for ((verticalIdx, line) in verticalTrees.withIndex()) {
            if (verticalIdx != 0 && verticalIdx != horizontalTrees[0].lastIndex) {
                for ((treeIdx, _) in line.withIndex()) {
                    if (treeIdx != 0 && treeIdx != horizontalTrees[0].lastIndex) {
                        val startCount = countFromStart(treeIdx, line)
                        val endCount = countFromEnd(treeIdx, line)
                        scoreMatrix[treeIdx][verticalIdx] *= (startCount * endCount)
                    }
                }
            }
        }
    }

    private fun countFromStart(fromIdx: Int, line: List<Int>): Int {
        val tree = line[fromIdx]
        var count = 0
        for (i in fromIdx - 1 downTo 0) {
            val prevTree = line[i]
            ++count
            if (tree <= prevTree) break
        }
        if (count == 0) ++count
        return count
    }

    private fun countFromEnd(fromIdx: Int, line: List<Int>): Int {
        val tree = line[fromIdx]
        var count = 0
        for (i in fromIdx + 1 until line.size) {
            val nextTree = line[i]
            ++count
            if (tree <= nextTree) break
        }
        return count
    }


    fun calculateVisibleTrees(): Int {
        val visibilities = buildVisibilities()
        var count = 0
        for (visibility in visibilities) {
            for (isVisible in visibility) {
                if (isVisible) ++count
            }
        }
        return count
    }

    private fun buildVisibilities(): List<BooleanArray> {
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
        return visibilities
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