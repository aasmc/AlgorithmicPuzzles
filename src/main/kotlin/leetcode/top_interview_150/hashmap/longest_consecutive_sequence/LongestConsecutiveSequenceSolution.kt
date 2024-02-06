package leetcode.top_interview_150.hashmap.longest_consecutive_sequence

class LongestConsecutiveSequenceSolution {

    fun longestConsecutive(nums: IntArray): Int {
        val set = nums.toHashSet()
        var count = 0
        for (num in nums) {
            // if this number starts a sequence
            var current = 0
            if (num - 1 !in set) {
                while (num + current in set) {
                    ++current
                }
                count = maxOf(count, current)
            }
        }
        return count
    }

}