package leetcode.ace_coding.strings.merge_strings

fun mergeAlternately(word1: String, word2: String): String {
    val builder = StringBuilder()
    var leftIdx = 0
    var rightIdx = 0
    var isLeft = true
    while (leftIdx < word1.length && rightIdx < word2.length) {
        if (isLeft) {
            builder.append(word1[leftIdx++])
        } else {
            builder.append(word2[rightIdx++])
        }
        isLeft = !isLeft
    }
    if (leftIdx < word1.length) {
        builder.append(word1.substring(leftIdx))
    }
    if (rightIdx < word2.length) {
        builder.append(word2.substring(rightIdx))
    }
    return builder.toString()
}