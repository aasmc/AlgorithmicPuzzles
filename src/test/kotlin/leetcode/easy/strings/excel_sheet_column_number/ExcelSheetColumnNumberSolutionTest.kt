package leetcode.easy.strings.excel_sheet_column_number

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class ExcelSheetColumnNumberSolutionTest {

    private val sut = ExcelSheetColumnNumberSolution()

    @Test
    fun testCorrect() {

        assertEquals(1, sut.titleToNumber("A"))
        assertEquals(28, sut.titleToNumber("AB"))
        assertEquals(52, sut.titleToNumber("AZ"))
        assertEquals(701, sut.titleToNumber("ZY"))
        assertEquals(2147483647, sut.titleToNumber("FXSHRXW"))

    }

}