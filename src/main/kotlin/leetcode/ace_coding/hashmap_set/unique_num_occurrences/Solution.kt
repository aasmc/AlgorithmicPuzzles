package leetcode.ace_coding.hashmap_set.unique_num_occurrences

fun uniqueOccurrences(arr: IntArray): Boolean {
    val m = hashMapOf<Int, Int>()
    arr.forEach { num ->
        m.merge(num, 1, Int::plus)
    }
    return m.values.toSet().size == m.size
}