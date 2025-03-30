package leetcode.medium.yandex_prep.find_all_anagrams

class FindAllAnagramsSolution {

    fun findAnagrams(s: String, p: String): List<Int> {
        if (p.length > s.length) return emptyList()

        val pCharToCount = hashMapOf<Char, Int>()
        val sCharToCount = hashMapOf<Char, Int>()
        for (i in p.indices) {
            pCharToCount.merge(p[i], 1, Int::plus)
            sCharToCount.merge(s[i], 1, Int::plus)
        }

        val result = mutableListOf<Int>()

        fun areFreqsEqual(): Boolean {
            for((pChar, pCount) in pCharToCount) {
                val sCount = sCharToCount[pChar] ?: return false
                if (sCount != pCount) {
                    return false
                }
            }
            return true
        }

        var start = 0
        if (areFreqsEqual()) {
            result.add(start)
        }
        for (right in p.length until s.length) {
            sCharToCount.merge(s[start++], -1, Int::plus)
            sCharToCount.merge(s[right], 1, Int::plus)
            if (areFreqsEqual()) {
                result.add(start)
            }
        }

        return result
    }

}