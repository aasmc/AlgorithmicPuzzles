package leetcode.top_interview_150.graph_general.clone_graph

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class CloneGraphSolutionTest {

    private val sut = CloneGraphSolution()

    @Test
    fun testCorrect() {

        val one = CloneGraphSolution.Node(1)
        val two = CloneGraphSolution.Node(2)
        val three = CloneGraphSolution.Node(3)
        val four = CloneGraphSolution.Node(4)

        one.neighbors.add(two)
        one.neighbors.add(four)
        two.neighbors.add(one)
        two.neighbors.add(three)
        three.neighbors.add(two)
        three.neighbors.add(four)
        four.neighbors.add(one)
        four.neighbors.add(three)

        val cloned = sut.cloneGraph(one)
        println(cloned)
    }

}
