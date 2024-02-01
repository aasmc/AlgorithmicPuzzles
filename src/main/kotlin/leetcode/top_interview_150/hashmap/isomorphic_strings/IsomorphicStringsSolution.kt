package leetcode.top_interview_150.hashmap.isomorphic_strings

class IsomorphicStringsSolution {

    fun isIsomorphic(s: String, t: String): Boolean {
        val sMap = hashMapOf<Char, Char>()
        val tMap = hashMapOf<Char, Char>()

        if (s.length != t.length) return false
        for (i in s.indices) {
            val ch1 = s[i]
            val ch2 = t[i]
            if ((sMap.containsKey(ch1) && sMap[ch1]!! != ch2)
                || (tMap.containsKey(ch2) && tMap[ch2] != ch1)) {
                return false
            }

            sMap[ch1] = ch2
            tMap[ch2] = ch1
        }
        return true
    }
}