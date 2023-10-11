package leetcode.ace_coding.prefix_sum.highest_altitude

fun largestAltitude(gain: IntArray): Int {
    var currentAlt = 0
    var maxAlt = currentAlt
    for (peak in gain) {
        currentAlt += peak
        maxAlt = maxOf(maxAlt, currentAlt)
    }
    return maxAlt
}