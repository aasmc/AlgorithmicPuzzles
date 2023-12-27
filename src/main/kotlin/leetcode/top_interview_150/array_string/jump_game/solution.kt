package leetcode.top_interview_150.array_string.jump_game

fun canJump(nums: IntArray): Boolean {
    // initially set the goal as the last index, since we need to find out if we can reach
    // the last index of the array
    var goal = nums.lastIndex
    // starting from the next to last index, check if we can reach the goal
    // and if true, move goal to the current idx
    for (prev in nums.lastIndex - 1 downTo 0) {
        if (prev + nums[prev] >= goal) {
            goal = prev
        }
    }
    // if we were able to move the goal post to index 0, then we can reach the last position
    // from the beginning of the array
    return goal == 0
}
