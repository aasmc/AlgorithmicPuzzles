package leetcode.medium.yandex_prep.permutation_in_string

class PermutationInString {

    fun checkInclusion(s1: String, s2: String): Boolean {
        if (s2.length < s1.length) return false
        val s1Frequency = hashMapOf<Char, Int>()
        s1.forEach { s1Frequency.merge(it, 1, Int::plus) }

        val s2Frequency = hashMapOf<Char, Int>()

        for (i in s1.indices) {
            s2Frequency.merge(s2[i], 1, Int::plus)
        }

        fun areFreqsEqual(): Boolean {
            for ((ch, count) in s1Frequency) {
                val s2Count = s2Frequency[ch] ?: return false
                if (s2Count != count) {
                    return false
                }
            }
            return true
        }

        if (areFreqsEqual()) return true

        var left = 0
        for (right in s1.length until s2.length) {
            s2Frequency.merge(s2[left++], -1, Int::plus)
            s2Frequency.merge(s2[right], 1, Int::plus)
            if (areFreqsEqual()) {
                return true
            }
        }
        return false
    }

}