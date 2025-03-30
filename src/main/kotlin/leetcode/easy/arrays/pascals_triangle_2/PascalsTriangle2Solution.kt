package leetcode.easy.arrays.pascals_triangle_2

class PascalsTriangle2Solution {

    fun getRow(rowIndex: Int): List<Int> {
        var result = mutableListOf<Int>()
        repeat(rowIndex + 1) {
            val currentRow = ArrayList<Int>(result.size + 1)
            for(i in 0..result.size) {
                if (i == 0 || i == result.size) {
                    currentRow.add(1)
                } else {
                    currentRow.add(result[i - 1] + result[i])
                }
            }
            result = currentRow
        }
        return result
    }

}