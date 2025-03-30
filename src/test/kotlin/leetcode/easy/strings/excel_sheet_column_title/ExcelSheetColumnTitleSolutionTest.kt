package leetcode.easy.strings.excel_sheet_column_title

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class ExcelSheetColumnTitleSolutionTest {

    private val sut = ExcelSheetColumnTitleSolution()

    @Test
    fun testCorrect() {
        assertEquals("A", sut.convertToTitle(1))
        assertEquals("B", sut.convertToTitle(2))
        assertEquals("AB", sut.convertToTitle(28))
        assertEquals("AZ", sut.convertToTitle(52))
        assertEquals("ZY", sut.convertToTitle(701))
    }

}