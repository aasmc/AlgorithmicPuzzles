package leetcode.top_interview_150.array_string.zigzag_conversion

class ZigzagConversionSolution {

    fun convert(s: String, numRows: Int): String {
        if (numRows == 1) return s
        val sb = StringBuilder()
        for (row in 0 until numRows) {
            val baseStep = (numRows - 1) * 2
            for (i in row until s.length step baseStep) {
                sb.append(s[i])
                val intermediateIdx = i + baseStep - 2 * row
                if (isIntermediateStep(row, numRows) && intermediateIdx < s.length) {
                    sb.append(s[intermediateIdx])
                }
            }
        }
        return sb.toString()
    }

    private fun isIntermediateStep(row: Int, numRows: Int) =
        row > 0 && row < numRows - 1


}