package leetcode.top_interview_150.hashmap.group_anagrams

class GroupAnagramsSolution {

    fun groupAnagrams(strs: Array<String>): List<List<String>> {
        return strs
            .groupBy { str -> str.groupingBy { it }.eachCount() }
            .values.toList()
    }

}