package leetcode.top_interview_150.two_pointers.water_container

class WaterContainerSolution {

    fun maxArea(height: IntArray): Int {
        if (height.size == 1) return 1

        var max = Int.MIN_VALUE
        var start = 0
        var end = height.lastIndex
        while (start <= end) {
            val diff = end - start
            val minHeight = minOf(height[start], height[end])
            val square = diff * minHeight
            max = maxOf(max, square)
            if (height[start] < height[end]) {
                ++start
            } else {
                --end
            }
        }
        return max
    }

}