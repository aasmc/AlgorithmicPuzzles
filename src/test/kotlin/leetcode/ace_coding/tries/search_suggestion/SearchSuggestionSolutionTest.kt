package leetcode.ace_coding.tries.search_suggestion

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class SearchSuggestionSolutionTest {

    private val sut = SearchSuggestionSolution()

    @Test
    fun testCorrect() {
        val products = arrayOf("mobile","mouse","moneypot","monitor","mousepad")
        val sw = "mouse"

        val res = sut.suggestedProducts(products, sw)
        for (l in res) {
            println(l)
        }
    }

    @Test
    fun testCorrect2() {
        val products = arrayOf("havana")
        val sw = "tatiana"

        val res = sut.suggestedProducts(products, sw)
        for (l in res) {
            println(l)
        }
    }

    @Test
    fun testCorrect3() {

    }

}