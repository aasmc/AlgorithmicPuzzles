package leetcode.ace_coding.strings_arrays.inc_triplet_subs

fun increasingTriplet(nums: IntArray): Boolean {
    // smallest element in increasing subsequence of sequence 0..n,
    // basically it is the start of increasing subsequence
    var first = Int.MAX_VALUE
    // the greatest element in increasing subsequence of size 2
    // (in the sequence from 0..n). This means, that if we find an element
    // that is larger than the second value, than we will find an increasing subsequence of size 3
    var second = Int.MAX_VALUE

    for (elem in nums) {
        if (elem > second) { // well, we found a third increasing element,
            return true
        }
        // if current element is larger than current minimum, than we have the second element
        // in the increasing subsequence
        if (elem > first) second = elem
        // if current element is smaller that current minimum, just update the min
        if (elem < first) first = elem
    }
    return false
}