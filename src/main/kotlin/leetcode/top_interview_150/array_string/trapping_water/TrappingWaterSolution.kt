package leetcode.top_interview_150.array_string.trapping_water

class TrappingWaterSolution {

    fun trap(height: IntArray): Int {
        var left = 0
        var right = height.lastIndex
        var maxLeft = height.first()
        var maxRight = height.last()
        var result = 0
        while (left < right) {
            if (maxLeft <= maxRight) {
                maxLeft = maxOf(maxLeft, height[++left])
                result += maxLeft - height[left]
            } else {
                maxRight = maxOf(maxRight, height[--right])
                result += maxRight - height[right]
            }
        }
        return result
    }

    fun trapWithAdditionalMemory(height: IntArray): Int {
        val leftMax = IntArray(height.size) { 0 }
        for (i in 1 until leftMax.size) {
            leftMax[i] = maxOf(leftMax[i - 1], height[i - 1])
        }
        val rightMax = IntArray(height.size) { 0 }
        for (i in rightMax.lastIndex - 1 downTo 0) {
            rightMax[i] = maxOf(rightMax[i + 1], height[i + 1])
        }
        var res = 0
        for (i in height.indices) {
            res += maxOf(minOf(leftMax[i], rightMax[i]) - height[i], 0)
        }
        return res
    }

}