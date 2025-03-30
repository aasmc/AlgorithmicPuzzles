package leetcode.easy.strings.isomorphic_strings

class IsomorphicStringsSolution {

    fun isIsomorphic(s: String, t: String): Boolean {
        if (s.length != t.length) return false
        val sMap = hashMapOf<Char, Char>()
        val tMap = hashMapOf<Char, Char>()
        for (i in s.indices) {
            val sChar = s[i]
            val tChar = t[i]
            if ((sMap.containsKey(sChar) && sMap[sChar] != tChar) ||
                tMap.containsKey(tChar) && tMap[tChar] != sChar) {
                return false
            }
            sMap[sChar] = tChar
            tMap[tChar] = sChar
        }
        return true
    }

}