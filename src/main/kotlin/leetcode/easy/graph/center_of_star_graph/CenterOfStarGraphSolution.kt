package leetcode.easy.graph.center_of_star_graph

class CenterOfStarGraphSolution {

    fun findCenter(edges: Array<IntArray>): Int {
        val (firstFrom, firstTo) = edges[0]
        val (secondFrom, secondTwo) = edges[1]

        if (firstFrom == secondFrom || firstFrom == secondTwo) {
            return firstFrom
        }
        return firstTo
    }

    fun findCenter2(edges: Array<IntArray>): Int {
        val vertexToDegree = hashMapOf<Int, Int>()
        edges.forEach { (from, to) ->
            vertexToDegree.merge(from, 1, Int::plus)
            vertexToDegree.merge(to, 1, Int::plus)
        }
        val iter = vertexToDegree.iterator()
        while (iter.hasNext()) {
            val (vertex, degree) = iter.next()
            if (degree == vertexToDegree.size - 1) {
                return vertex
            }
        }
        return -1
    }

}