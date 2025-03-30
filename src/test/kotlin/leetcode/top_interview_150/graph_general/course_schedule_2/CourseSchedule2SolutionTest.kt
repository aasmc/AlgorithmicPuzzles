package leetcode.top_interview_150.graph_general.course_schedule_2

import org.junit.jupiter.api.Test
import kotlin.test.assertContentEquals

class CourseSchedule2SolutionTest {

    private val sut = CourseSchedule2Solution()

    @Test
    fun testCorrect() {

        assertContentEquals(intArrayOf(0,1), sut.findOrder(2, arrayOf(intArrayOf(1, 0))))
        assertContentEquals(intArrayOf(), sut.findOrder(2, arrayOf(
            intArrayOf(1,0),
            intArrayOf(0,1)
        )))
        assertContentEquals(intArrayOf(0,1,2,3), sut.findOrder(4, arrayOf(
            intArrayOf(1,0),
            intArrayOf(2,0),
            intArrayOf(3,1),
            intArrayOf(3,2),
            )))

    }

}