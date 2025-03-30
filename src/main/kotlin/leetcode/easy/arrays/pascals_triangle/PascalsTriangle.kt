package leetcode.easy.arrays.pascals_triangle

class PascalsTriangle {

    fun generate(numRows: Int): List<List<Int>> {
        val result = mutableListOf<List<Int>>()
        var prevLine = mutableListOf<Int>()
        repeat(numRows) {
            val currentLine = mutableListOf<Int>()
            for (i in 0..prevLine.size) {
                if (i == 0 || i == prevLine.size) {
                    currentLine.add(1)
                } else {
                    currentLine.add(prevLine[i - 1] + prevLine[i])
                }
            }
            result.add(currentLine)
            prevLine = currentLine
        }
        return result
    }

}