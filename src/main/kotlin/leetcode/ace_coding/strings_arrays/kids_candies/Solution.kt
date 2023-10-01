package leetcode.ace_coding.strings_arrays.kids_candies

fun kidsWithCandies(candies: IntArray, extraCandies: Int): List<Boolean> {
    val currentMax = candies.maxBy { it }
    return candies.map {
        it + extraCandies >= currentMax
    }
}