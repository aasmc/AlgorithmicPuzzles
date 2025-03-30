package leetcode.medium.yandex_prep.partition_labels

class PartitionLabelsSolution {

    fun partitionLabels(s: String): List<Int> {
        val charToLastIdx = hashMapOf<Char, Int>()

        s.forEachIndexed { index, ch ->
            charToLastIdx[ch] = index
        }

        val result = mutableListOf<Int>()
        var end = 0
        var size = 0
        for (i in s.indices) {
            end = maxOf(end, charToLastIdx[s[i]]!!)
            ++size
            if (i == end) {
                result.add(size)
                size = 0
            }
        }
        return result
    }

}