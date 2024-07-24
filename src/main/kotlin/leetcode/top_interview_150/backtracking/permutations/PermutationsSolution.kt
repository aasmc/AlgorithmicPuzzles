package leetcode.top_interview_150.backtracking.permutations

class PermutationsSolution {

    fun permute(nums: IntArray): List<List<Int>> {
        val result = mutableListOf<List<Int>>()
        fun backtrack(used: MutableSet<Int>) {
            if (nums.size == used.size) {
                result.add(used.toList())
                return
            }

            for (num in nums) {
                if (num in used) continue
                used.add(num)
                backtrack(used)
                used.remove(num)
            }
        }
        backtrack(mutableSetOf<Int>())

        return result
    }



    fun permute2(nums: IntArray): List<List<Int>> {
        val result = mutableListOf<List<Int>>()
        val visited = hashSetOf<Int>()
        fun backTrack(currentIdx: Int, permutation: List<Int>) {
            if (permutation.size == nums.size) {
                result.add(permutation)
                return
            }
            visited.add(nums[currentIdx])
            for (i in nums.indices) {
                if (nums[i] !in visited) {
                    backTrack(i, permutation + nums[i])
                }
            }
            visited.remove(nums[currentIdx])
        }
        for (i in nums.indices) {
            backTrack(i, listOf(nums[i]))
        }
        return result
    }

}