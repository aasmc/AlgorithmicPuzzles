package leetcode.easy.strings.excel_sheet_column_title

class ExcelSheetColumnTitleSolution {

    private val alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
    private val colToChar = hashMapOf<Int, Char>()

    init {
        alphabet.forEachIndexed { index, c ->
            colToChar[index] = c
        }
    }

    fun convertToTitle(columnNumber: Int): String {
        var col = columnNumber
        val sb = StringBuilder()
        while (col > 0) {
            val mod = (col - 1) % 26
            sb.append(colToChar[mod]!!)
            col = (col - 1) / 26
        }
        return sb.toString().reversed()
    }

}