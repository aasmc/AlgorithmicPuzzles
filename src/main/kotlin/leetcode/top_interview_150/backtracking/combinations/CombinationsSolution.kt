package leetcode.top_interview_150.backtracking.combinations

import java.util.LinkedList

class CombinationsSolution {

    fun combine(n: Int, k: Int): List<List<Int>> {

        val result = mutableListOf<List<Int>>()
        fun backTrack(curNum: Int, combination: MutableList<Int>) {
            if (combination.size == k) {
                result.add(combination.toList())
                return
            }
            for (i in curNum..n) {
                combination.add(i)
                backTrack(i + 1, combination)
                combination.removeLast()
            }
        }
        backTrack(1, mutableListOf())

        return result
    }

    fun combine2(n: Int, k: Int): List<List<Int>> {
        val result = mutableListOf<List<Int>>()
        fun backTrack(curNum: Int, combination: List<Int>) {
            if (combination.size == k) {
                result.add(combination)
                return
            }
            for (i in curNum..n) {
                backTrack(i + 1, combination + i)
            }
        }
        backTrack(1, listOf())
        return result
    }

}