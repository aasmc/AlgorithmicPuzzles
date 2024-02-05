package leetcode.top_interview_150.hashmap.group_anagrams

class GroupAnagramsSolution {

    fun groupAnagrams(strs: Array<String>): List<List<String>> {
        val map = hashMapOf<String, MutableList<String>>()
        strs.forEach { str ->
            if (map.isEmpty()) {
                map[str] = arrayListOf(str)
            } else {
                var isNew = true
                for (key in map.keys) {
                    if (isAnagram(key, str)) {
                        map[key]!!.add(str)
                        isNew = false
                        break
                    }
                }
                if(isNew) {
                    map[str] = arrayListOf(str)
                }
            }
        }
        return map.values.toList()
    }

    fun isAnagram(s: String, t: String): Boolean {
        if (s.length != t.length) return false
        val map = hashMapOf<Char, Int>()
        s.forEach { ch -> map.merge(ch, 1, Int::plus) }
        for (ch in t) {
            map.merge(ch, -1, Int::plus)
            if (map[ch]!! <0) return false
        }
        return true
    }

}