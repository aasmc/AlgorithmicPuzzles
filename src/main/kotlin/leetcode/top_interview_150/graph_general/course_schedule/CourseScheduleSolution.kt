package leetcode.top_interview_150.graph_general.course_schedule

class CourseScheduleSolution {

    fun canFinish(numCourses: Int, prerequisites: Array<IntArray>): Boolean {
        val graph = hashMapOf<Int, MutableList<Int>>()
        for (i in 0 until numCourses) {
            graph[i] = mutableListOf()
        }
        prerequisites.forEach { (from, to) ->
            graph[from]!!.add(to)
        }
        val visited = hashSetOf<Int>()

        fun dfs(at: Int): Boolean {
            if (at in visited) {
                return false
            }
            if (graph[at]!!.isEmpty()) {
                return true
            }
            visited.add(at)
            for (prerequisite in graph[at]!!) {
                if (!dfs(prerequisite)) {
                    return false
                }
            }
            visited.remove(at)
            graph[at] = mutableListOf()
            return true
        }
        for (course in 0 until numCourses) {
            if (!dfs(course)) {
                return false
            }
        }
        return true
    }

}