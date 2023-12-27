package leetcode.top_interview_150.array_string.jump_game_two

class JumpGameTwoSolution {

    fun jump(nums: IntArray): Int {
        var res = 0
        var left = 0
        var right = 0
        while (right < nums.lastIndex) {
            var farthest = 0
            for (i in left..right) {
                farthest = maxOf(farthest, i + nums[i])
            }
            left = right + 1
            right = farthest
            ++res
        }
        return res
    }


    fun jumpDpInefficient(nums: IntArray): Int {
        val dp = IntArray(nums.size) { Int.MAX_VALUE }
        dp[0] = 0
        for (i in 0 until nums.lastIndex) {
            val currentJum = nums[i]
            for (j in 1 .. currentJum) {
                if (i + j < nums.size) {
                    dp[i + j] = minOf(dp[i] + 1, dp[i + j])
                }
            }
        }
        return dp[dp.lastIndex]
    }

}