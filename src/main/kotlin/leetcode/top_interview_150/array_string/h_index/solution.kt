package leetcode.top_interview_150.array_string.h_index

class HIndexSolution {

    fun hIndex(citations: IntArray): Int {
        citations.sort()
        val size = citations.size
        for (i in citations.indices) {
            val curNumber = citations[i]
            val left = size - i
            if (curNumber >= left) return left
        }
        return citations[0]
    }

}