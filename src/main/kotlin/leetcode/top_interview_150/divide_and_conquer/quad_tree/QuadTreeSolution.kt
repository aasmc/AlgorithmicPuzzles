package leetcode.top_interview_150.divide_and_conquer.quad_tree

class QuadTreeSolution {

    class Node(var `val`: Boolean, var isLeaf: Boolean) {
        var topLeft: Node? = null
        var topRight: Node? = null
        var bottomLeft: Node? = null
        var bottomRight: Node? = null
        override fun toString(): String {
            return "Node: {value=${`val`}, isLeaf=$isLeaf, " +
                    "topLeft=${if (topLeft == null) null else topLeft}, " +
                    "topRight=${if (topRight == null) null else topRight}," +
                    "bottomLeft=${if (bottomLeft == null) null else bottomLeft}," +
                    "bottomRight=${if (bottomRight == null) null else bottomRight}}"
        }
    }

    fun construct(grid: Array<IntArray>): Node? {
        if (grid.isEmpty()) return null
        return construct(grid, grid.size, 0, 0)
    }

    private fun construct(grid: Array<IntArray>, size: Int, row: Int, col: Int): Node? {
        val isLeaf = isLeaf(grid, size, row, col)
        if (isLeaf) {
            return Node(grid[row][col] == 1, true)
        }
        val newSize = size / 2
        val topLeft = construct(grid, newSize, row, col)
        val topRight = construct(grid, newSize, row, col + newSize)
        val bottomLeft = construct(grid, newSize, row + newSize, col)
        val bottomRight = construct(grid, newSize, row + newSize, col + newSize)
        val node = Node(false, false)
        node.topLeft = topLeft
        node.topRight = topRight
        node.bottomLeft = bottomLeft
        node.bottomRight = bottomRight
        return node
    }

    private fun isLeaf(grid: Array<IntArray>, size: Int, row: Int, col: Int): Boolean {
        var allSame = true
        for (r in 0 until size) {
            for (c in 0 until size) {
                if (grid[row][col] != grid[row + r][col + c]) {
                    allSame = false
                    break
                }
            }
        }
        return allSame
    }

}