package leetcode.ace_coding.strings_arrays.flowers


fun canPlaceFlowers(flowerbed: IntArray, n: Int): Boolean {
    var count = 0
    for (i in flowerbed.indices) {
        if (flowerbed[i] == 0) {
            val leftEmpty = if (i == 0) true else flowerbed[i - 1] == 0
            val rightEmpty = if (i == flowerbed.lastIndex) true else flowerbed[i + 1] == 0
            if (leftEmpty && rightEmpty) {
                ++count
                if (count >= n) return true
                flowerbed[i] = 1
            }
        }
    }

    return count >= n
}