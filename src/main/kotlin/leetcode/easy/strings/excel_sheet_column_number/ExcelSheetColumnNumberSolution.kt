package leetcode.easy.strings.excel_sheet_column_number

class ExcelSheetColumnNumberSolution {
    fun titleToNumber1(columnTitle: String): Int {
        var res = 0
        var pow = 1
        for (i in columnTitle.lastIndex downTo 0) {
            val digit = (columnTitle[i] - 'A' + 1) * pow
            pow *= 26
            res += digit
        }
        return res
    }

    fun titleToNumber(columnTitle: String) =
        columnTitle.map { ch ->
            ch - 'A' + 1
        }.fold(0) { acc, number ->
            acc * 26 + number
        }
}