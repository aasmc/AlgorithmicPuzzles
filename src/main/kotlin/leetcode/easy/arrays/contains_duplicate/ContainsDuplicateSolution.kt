package leetcode.easy.arrays.contains_duplicate

class ContainsDuplicateSolution {

    fun containsDuplicate(nums: IntArray): Boolean {
        val set = hashSetOf<Int>()
        nums.forEach { num ->
            if (set.contains(num)) {
                return true
            }
            set.add(num)
        }
        return false
    }

    fun containsDuplicate2(nums: IntArray): Boolean {
        val numToCount = hashMapOf<Int, Int>()
        nums.forEach { num ->
            numToCount.merge(num, 1, Int::plus)
        }
        return numToCount.values.any { it > 1 }
    }

}