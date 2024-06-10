package leetcode.top_interview_150.array_string.h_index

class HIndexSolution {

    fun hIndex2(citations: IntArray): Int {
        citations.sort()
        val size = citations.size
        for (i in citations.indices) {
            val curNumber = citations[i]
            val left = size - i
            if (curNumber >= left) return left
        }
        return citations[0]
    }

    fun hIndex(citations: IntArray): Int {
        if (citations.isEmpty()) return 0
        val size = citations.size
        val buckets = IntArray(size + 1)
        citations.forEach { cit ->
            if (cit > size) {
                buckets[size]++
            } else {
                buckets[cit]++
            }
        }
        var idx = 0
        buckets.forEachIndexed { number, count ->
            for (i in 0 until count) {
                citations[idx++] = number
            }
        }
        for (i in citations.indices) {
            val current = citations[i]
            val left = size - i
            if (current >= left) return left
        }
        return citations[0]
    }

}