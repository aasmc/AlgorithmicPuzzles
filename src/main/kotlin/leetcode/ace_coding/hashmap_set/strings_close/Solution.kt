package leetcode.ace_coding.hashmap_set.strings_close

fun closeStrings(word1: String, word2: String): Boolean {
    if (word1.length != word2.length) return false

    val first = word1.toCharArray()
    first.sort()
    val second = word2.toCharArray()
    second.sort()
    if (first.contentEquals(second)) {
        return true
    }
    val firstOccurrences = hashMapOf<Char, Int>()
    val secondOccurrences = hashMapOf<Char, Int>()
    first.forEach { ch ->
        firstOccurrences.merge(ch, 1, Int::plus)
    }
    second.forEach { ch ->
        secondOccurrences.merge(ch, 1, Int::plus)
    }
    if (firstOccurrences.keys != secondOccurrences.keys) return false
    return firstOccurrences.values.sorted() == secondOccurrences.values.sorted()
}