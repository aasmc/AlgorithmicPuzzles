package leetcode.ace_coding.strings_arrays.reversevowels

fun reverseVowels(s: String): String {
    val vowels = hashSetOf<Char>(
        'a', 'e', 'i', 'o', 'u', 'A', 'E', 'I', 'O', 'U'
    )
    var start = 0
    var end = s.length - 1
    val chars = s.toCharArray()
    while (start <= end) {
        while (start <= end && !vowels.contains(chars[start])) {
            ++start
        }
        while (end >= start && !vowels.contains(chars[end])) {
            --end
        }
        if (start <= end) {
            val tmp = chars[start]
            chars[start] = chars[end]
            chars[end] = tmp
        }
        ++start
        --end
    }
    return StringBuilder().append(chars).toString()
}