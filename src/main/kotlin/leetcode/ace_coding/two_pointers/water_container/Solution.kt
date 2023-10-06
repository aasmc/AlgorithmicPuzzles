package leetcode.ace_coding.two_pointers.water_container


fun maxArea(height: IntArray): Int {
    if (height.size == 1) return 0

    var start = 0
    var end = height.lastIndex
    var max = Int.MIN_VALUE
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