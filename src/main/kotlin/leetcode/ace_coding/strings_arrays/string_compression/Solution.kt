package leetcode.ace_coding.strings_arrays.string_compression

fun compressEditorial(chars: CharArray): Int {
    var i = 0
    var res = 0
    while (i < chars.size) {
        var groupLength = 1
        while (i + groupLength < chars.size && chars[i] == chars[i + groupLength]) {
            ++groupLength
        }
        chars[res++] = chars[i]
        if (groupLength > 1) {
            for(d in groupLength.toString().toCharArray()) {
                chars[res++] = d
            }
        }
        i += groupLength
    }
    return res
}


fun compress(chars: CharArray): Int {
    if (chars.size == 1) return 1

    var size = 0
    var firstIdx = 0
    var charInsIdx: Int = 0
    var digitInsIdx: Int
    for (secondIdx in 1 until chars.size) {
        if (chars[secondIdx] != chars[firstIdx]) {
            digitInsIdx = charInsIdx + 1
            chars[charInsIdx] = chars[firstIdx]
            ++size
            val diff = secondIdx - firstIdx
            if (diff > 1) {
                if (diff >= 10) {
                    val digits = diff.toString().toCharArray()
                    for (d in digits) {
                        chars[digitInsIdx++] = d
                        ++size
                    }
                } else {
                    val d = diff.toString()[0]
                    chars[digitInsIdx++] = d
                    ++size
                }
            }
            charInsIdx = digitInsIdx
            firstIdx = secondIdx
        }
    }
    val diff = chars.size - firstIdx
    ++size
    chars[charInsIdx] = chars[firstIdx]

    if (diff > 1) {
        digitInsIdx = charInsIdx + 1
        if (diff >= 10) {
            val digits = diff.toString().toCharArray()
            for (d in digits) {
                chars[digitInsIdx++] = d
                ++size
            }
        } else {
            val d = diff.toString()[0]
            chars[digitInsIdx] = d
            ++size
        }
    }
    return size
}


