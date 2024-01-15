package leetcode.top_interview_150.backtracking.unique_permutations

class UniquePermutationsSolution {

    fun permuteUnique(nums: IntArray): List<List<Int>> {
        val sequence = permutationsUnique(nums)
        val result = arrayListOf<List<Int>>()
        sequence.forEach {
            result.add(it.toList())
        }
        return result
    }

    private fun permutationsUnique(nums: IntArray): Sequence<List<Int>> = sequence {
        val numToCount = hashMapOf<Int, Int>()
        nums.forEach { num ->
            numToCount.merge(num, 1, Int::plus)
        }
        val permutation = mutableListOf<Int>()

        suspend fun SequenceScope<List<Int>>.backTrack() {
            if (permutation.size == nums.size) {
                yield(permutation)
            } else {
                for((num, count) in numToCount) {
                    if (count > 0) {
                        permutation.add(num)
                        numToCount[num] = numToCount[num]!! - 1
                        backTrack()
                        numToCount[num] = numToCount[num]!! + 1
                        permutation.removeAt(permutation.lastIndex)
                    }
                }
            }
        }
        backTrack()
    }

}