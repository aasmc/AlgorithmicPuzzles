package leetcode.medium.yandex_prep.partition_labels

class PartitionLabelsSolution {

    fun partitionLabels(s: String): List<Int> {
        val charToLastIdx = hashMapOf<Char, Int>()

        s.forEachIndexed { index, ch ->
            charToLastIdx.merge(ch, index) { _, new ->
                new
            }
        }

        val result = mutableListOf<Int>()
        var end = 0
        var size = 0
        for (i in s.indices) {
            val ch = s[i]
            if (end < charToLastIdx[ch]!!) {
                end = charToLastIdx[ch]!!
            }
            ++size
            if (i == end) {
                result.add(size)
                size = 0
            }
        }
        return result
    }

}