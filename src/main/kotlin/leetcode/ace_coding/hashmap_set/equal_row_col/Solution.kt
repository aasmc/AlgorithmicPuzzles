package leetcode.ace_coding.hashmap_set.equal_row_col

fun equalPairs(grid: Array<IntArray>): Int {
    var count = 0
    for (i in grid.indices) {
        val row = grid[i]
        for (j in grid.indices) {
            var rowEqualsColl = true
            for (k in grid.indices) {
                val collCell = grid[k][j]
                val rowCell = row[k]
                if (rowCell != collCell) {
                    rowEqualsColl = false
                }
            }
            if (rowEqualsColl) {
                ++count
            }
        }
    }
    return count
}