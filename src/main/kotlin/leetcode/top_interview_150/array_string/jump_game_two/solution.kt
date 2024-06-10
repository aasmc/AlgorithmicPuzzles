package leetcode.top_interview_150.array_string.jump_game_two

class JumpGameTwoSolution {

    fun jump(nums: IntArray): Int {
        // stores the number of jumps needed to reach a level
        var res = 0
        // left points to the beginning of window, right points to the end
        // of window, where window represents indices in the array,
        // that are reachable from previous window:
        // e.g. we have array = [2, 3, 1, 1, 4], and we are at index 0
        // initially our window is equal to: left=0 and right=0, and res=0
        // since we don't need to jump to that position
        // from index 0 we can reach index 1 and index 2,
        // therefore window: left=1, right=2, and res=1, since we can reach
        // the window in 1 jump from index 0,
        // from this second window we can reach index 3 and index 4 in 1 jump,
        // therefore our next window: left=3, right=4, and res=2, since we can
        // reach it in two jumps from starting position
        var left = 0
        var right = 0
        while (right < nums.lastIndex) {
            val farthest = selectFarthestJump(left, right, nums)
            left = right + 1
            right = farthest
            ++res
        }
        return res
    }

    private fun selectFarthestJump(left: Int, right: Int, nums: IntArray): Int {
        var farthest = 0
        for (i in left..right) {
            farthest = maxOf(farthest, i + nums[i])
        }
        return farthest
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