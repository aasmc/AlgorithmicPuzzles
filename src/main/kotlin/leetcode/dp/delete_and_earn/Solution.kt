package leetcode.dp.delete_and_earn


fun deleteAndEarn(nums: IntArray): Int {
    // since we can have duplicates, count the number of occurrences of all numbers
    val count = hashMapOf<Int, Int>()
    nums.forEach { n ->
        count.merge(n, 1, Int::plus)
    }
    // remove duplicates and sort the list
    val uniqueSorted = nums.toSortedSet().toIntArray()
    val dp = IntArray(uniqueSorted.size) { 0 }
    dp[0] = uniqueSorted[0] * count[uniqueSorted[0]]!!
    for (i in 1 until uniqueSorted.size) {
        val current = uniqueSorted[i] * count[uniqueSorted[i]]!!
        if (uniqueSorted[i] == uniqueSorted[i - 1] + 1) {
            val prev = if (i > 1) dp[i - 2] else 0
            dp[i] = maxOf(prev + current, dp[i - 1])
        } else {
            dp[i] = dp[i - 1] + current
        }
    }
    return dp[dp.lastIndex]
}

fun deleteAndEarn2(nums: IntArray): Int {
    // since we can have duplicates, count the number of occurrences of all numbers
    val count = hashMapOf<Int, Int>()
    nums.forEach { n ->
        count.merge(n, 1, Int::plus)
    }
    // remove duplicates and sort the list
    val uniqueSorted = nums.toSortedSet().toList()
    // represents what we could earn at position i - 2 (we know for sure that this number
    // can be included in the result, because it is definitely less than the current number
    // by more than 1)
    var earn1 = 0
    // represents what we could earn at position i - 1 (we may not be allowed to include
    // it in the result, because previous number could be less than the current number
    // by 1
    var earn2 = 0
    for (i in uniqueSorted.indices) {
        // what we could earn by deleting current number
        val currentEarn = uniqueSorted[i] * count[uniqueSorted[i]]!!
        // save previous amount to a temp value
        val tmp = earn2
        earn2 = if (i > 0 && uniqueSorted[i] == uniqueSorted[i - 1] + 1) {
            // if cannot include previous value because it is less than the
            // current number by 1, then we have to choose either current value
            // plus the value at i - 2, or value at i - 1
            maxOf(currentEarn + earn1, earn2)
        } else {
            // if we can include previous value, add it to current value
            currentEarn + earn2
        }
        earn1 = tmp
    }
    return earn2
}