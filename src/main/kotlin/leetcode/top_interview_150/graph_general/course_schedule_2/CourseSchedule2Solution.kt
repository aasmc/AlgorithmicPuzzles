package leetcode.top_interview_150.graph_general.course_schedule_2

class CourseSchedule2Solution {

    fun findOrder(numCourses: Int, prerequisites: Array<IntArray>): IntArray {
        val courseToPrerequisites = hashMapOf<Int, MutableList<Int>>()
        for (i in 0 until numCourses) {
            courseToPrerequisites[i] = mutableListOf()
        }
        for ((course, prerequisite) in prerequisites) {
            courseToPrerequisites[course]!!.add(prerequisite)
        }
        // stores courses that are on the dfs stack. needed to detect cycles
        // there's a cycle, if current course in the dfs has already been
        // placed on the currentPath.
        val currentPath = hashSetOf<Int>()
        val ordering = IntArray(numCourses)
        // keeps track of the visited nodes globally
        val visited = BooleanArray(numCourses)
        var idx = 0

        fun dfs(course: Int): Boolean {
            // if we are in a cycle
            if (course in currentPath) {
                return false
            }
            // if we already visited the course, this means we can complete it,
            // so return true
            if (visited[course]) {
                return true
            }
            // add this course to the current path
            currentPath.add(course)
            // traverse all prerequisites of the course
            for(prerequisite in courseToPrerequisites[course]!!) {
                if (!dfs(prerequisite)) {
                    return false
                }
            }
            // remove this course from the current path
            currentPath.remove(course)
            visited[course] = true
            ordering[idx++] = course
            return true
        }

        for(course in 0 until numCourses) {
            if (!dfs(course)) {
                return intArrayOf()
            }
        }

        return ordering
    }

}