package leetcode.easy.two_pointers.assign_cookies

class AssignCookiesSolution {

    fun findContentChildren(g: IntArray, s: IntArray): Int {
        g.sort()
        s.sort()

        var gIdx = 0
        var sIdx = 0
        var count = 0
        while (gIdx < g.size && sIdx < s.size) {
            if (g[gIdx] <= s[sIdx]) {
                ++count
                ++gIdx
                ++sIdx
            } else {
                ++sIdx
            }
        }
        return count
    }

}