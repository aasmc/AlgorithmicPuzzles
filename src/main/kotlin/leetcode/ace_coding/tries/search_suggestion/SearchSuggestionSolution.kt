package leetcode.ace_coding.tries.search_suggestion

class SearchSuggestionSolution {

    fun suggestedProducts(products: Array<String>, searchWord: String): List<List<String>> {
        val res = mutableListOf<MutableList<String>>()
        products.sort()
        var left = 0
        var right = products.lastIndex
        for (i in searchWord.indices) {
            val prefix = searchWord.substring(0, i + 1)
            while (left < products.size && !products[left].startsWith(prefix)) {
                ++left
            }
            while (right >= 0 && !products[right].startsWith(prefix)) {
                --right
            }
            var cnt = 0
            val interim = mutableListOf<String>()
            for (j in left..right) {
                ++cnt
                interim.add(products[j])
                if (cnt == 3) {
                    break
                }
            }
            res.add(interim)
        }

        return res
    }


}