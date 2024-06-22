package leetcode.easy.sliding_window.minimum_recolors

class MinimumRecolorsSolution {

    fun minimumRecolors(blocks: String, k: Int): Int {
        var count = 0
        for (i in 0 until k) {
            if (blocks[i] == 'W') {
                ++count
            }
        }
        var left = 0
        var current = count
        for (right in k until blocks.length) {
            val toSubtract = if (blocks[left++] == 'W') 1 else 0
            val toAdd = if (blocks[right] == 'W') 1 else 0
            current = current - toSubtract + toAdd
            count = minOf(count, current)
        }
        return count
    }

}