package sedgewick_algorithms_c.chapter_17.client

import sedgewick_algorithms_c.chapter_17.adt.Graph

class DegreeCounter(
    graph: Graph
) {
    private val outDegree: MutableList<Int> = arrayListOf()
    private val inDegree: MutableMap<Int, Int> = hashMapOf()

    init {
        for (i in 0 until graph.vertexCount()) {
            outDegree.add(i, 0)
            val iterator = graph.adjIterator(i)
            var next = iterator.beg()
            while (!iterator.end()) {
                inDegree.merge(next, 1, Int::plus)
                outDegree[i]++
                next = iterator.nxt()
            }
        }
    }

    fun getOutDegree(vertex: Int): Int {
        return outDegree[vertex]
    }

    fun getInDegree(vertex: Int): Int {
        return inDegree[vertex] ?: 0
    }
}