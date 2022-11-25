package sedgewick_algorithms_c.chapter_17.client

import sedgewick_algorithms_c.chapter_17.adt.Graph

class DegreeCounter(
    private val graph: Graph
) {
    private val degree: MutableList<Int> = arrayListOf()

    init {
        for (i in 0 until graph.vertexCount()) {
            degree.add(i, 0)
            val iterator = graph.adjIterator(i)
            var next = iterator.beg()
            while (!iterator.end()) {
                degree[i]++
                next = iterator.nxt()
            }
        }
    }

    operator fun get(vertex: Int): Int {
        return degree[vertex]
    }
}