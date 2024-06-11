package leetcode.top_interview_150.array_string.candy

class CandySolution {

    fun candy(ratings: IntArray): Int {
        val candies = IntArray(ratings.size) { 1 }
        // this loop ensures that a particular guy has more candies than the one to the
        // left of him, if this guy's rating is greater than the rating of the guy to
        // the left of him
        for (i in 1 until ratings.size) {
            if (hasGreaterRatingThanTheLeftNeighbour(ratings, i)) {
                candies[i] = candies[i - 1] + 1
            }
        }
        // this loop ensures that a particular guy has more candies than the one to the
        // right of him, if this guy's rating is greater than the rating of the guy to
        // the right of him, but in this case we must consider the case when this
        // guy already has more candies than the guy to the right of him (this may happen
        // on the first pass in the loop above), in this case we don't change the number
        // of candies of this guy
        for (i in ratings.lastIndex - 1 downTo 0) {
            if (hasGreaterRatingThanTheRightNeighbour(ratings, i)) {
                candies[i] = maxOf(candies[i], candies[i + 1] + 1)
            }
        }
        return candies.sumOf { it }
    }

    private fun hasGreaterRatingThanTheRightNeighbour(ratings: IntArray, i: Int) =
        ratings[i] > ratings[i + 1]

    private fun hasGreaterRatingThanTheLeftNeighbour(ratings: IntArray, i: Int) =
        ratings[i - 1] < ratings[i]

}