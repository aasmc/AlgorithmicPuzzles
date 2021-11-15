package leetcode.algo_study_plan01.rotate_array

fun rotateByReversing(nums: IntArray, k: Int): Unit {
    if (k == nums.size) return
    val boundary = k % nums.size
    val endOfFirstPart = nums.lastIndex - boundary

    // reverse first part of the array (all except last k elements, since
    // we are going to rotate the array to the right)
    reverse(nums, 0, endOfFirstPart)
    // reverse last part of the array (last k elements)
    reverse(nums, endOfFirstPart + 1, nums.lastIndex)
    // reverse the entire array
    reverse(nums, 0, nums.lastIndex)

}

fun reverse(array: IntArray, from_: Int, to_: Int) {
    if (from_ == to_) return
    var end = to_
    for (i in from_..(from_ + to_) / 2) {
        val tmp = array[i]
        array[i] = array[end]
        array[end] = tmp
        end--
    }
}