package leetcode.ace_coding.sliding_window.max_vowels_in_substr

fun maxVowels(s: String, k: Int): Int {
    val vowels = hashSetOf<Char>('a', 'e', 'i', 'o', 'u')

    val arr = s.toCharArray()

    var curMax = 0
    for (i in 0 until k) {
        if (vowels.contains(arr[i])) {
            ++curMax
        }
    }
    var max = curMax
    for (i in k until s.length) {
        if (vowels.contains(arr[i -k])) {
            --curMax
        }
        if (vowels.contains(arr[i])) {
            ++curMax
        }
        max = maxOf(max, curMax)
    }
    return max
}