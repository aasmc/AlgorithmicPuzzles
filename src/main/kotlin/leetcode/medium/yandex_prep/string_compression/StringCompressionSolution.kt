package leetcode.medium.yandex_prep.string_compression

class StringCompressionSolution {

    fun compress(chars: CharArray): Int {
        var idx = 0
        var i = 0
        while (i < chars.size) {
            var groupSize = 1
            while (i + groupSize < chars.size && chars[i] == chars[i + groupSize]) {
                ++groupSize
            }
            chars[idx++] = chars[i]
            if (groupSize > 1) {
                for (d in groupSize.toString().toCharArray()) {
                    chars[idx++] = d
                }
            }
            i += groupSize
        }
        return idx
    }

}