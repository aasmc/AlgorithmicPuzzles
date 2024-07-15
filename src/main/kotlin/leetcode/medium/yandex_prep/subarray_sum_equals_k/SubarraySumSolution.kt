package leetcode.medium.yandex_prep.subarray_sum_equals_k

class SubarraySumSolution {

    fun subarraySum(nums: IntArray, k: Int): Int {
        var count = 0
        // intuition:
        // for every number in the array we compute the prefix sum of that number
        // after that we compute the difference between current prefix sum and k,
        // this difference means, that we can remove the prefix sum equal to the difference
        // from the current sum and receive the expected sum equal to k, but there may be
        // several prefix sums, equal to the difference, therefore we need to maintain
        // a count of all prefix sums. We will use a hashmap for that. This count of
        // difference prefix sums is the number of subarrays equal to k until this
        // num (including).
        //
        // stores counts of prefix sums for every number in the nums array
        // initially count of 0 prefix sum is equal to 1, because we can encounter
        // the situation when currentPrefixSum == k, therefore
        // difference = currentPrefixSum - k = 0, and we need to include this
        // subarray as well.
        // E.g. nums = [1,1,1], k = 2
        // initial setup: currentPrefixSum = 0  map = {0 to 1}, count = 0
        // i = 0, currentPrefixSum = 1, diff = 2 - 1 = 1, map = {0 to 1, 1 to 1}
        // i = 1, currentPrefixSum = 2, diff = 2 - 2 = 0, map = {0 to 1, 1 to 1, 2 to 1}, map contains diff --> count += 1
        // i = 2, currentPrefixSum = 3, diff = 3 - 2 = 1, map = {0 to 1, 1 to 1, 2 to 1, 3 to 1}, map contains diff --> count += 1
        // return count = 2
        val prefixSumToCount = hashMapOf(0 to 1)
        var sum = 0
        for (num in nums) {
            sum += num
            val prefix = sum - k
            if (prefixSumToCount.containsKey(prefix)) {
                count += prefixSumToCount[prefix]!!
            }
            prefixSumToCount.merge(sum, 1, Int::plus)
        }
        return count
    }

}