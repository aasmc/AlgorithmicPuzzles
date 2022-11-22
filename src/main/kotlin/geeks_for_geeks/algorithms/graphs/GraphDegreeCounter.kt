package geeks_for_geeks.algorithms.graphs

class GraphDegreeCounter<V: Comparable<V>>(
    private val graph: Graph<V>
) {
    private val degreeList: MutableList<Int> = ArrayList(graph.getVertexNumber())
    init {
        for (v in graph.vertices()) {
            val vIdx = graph.getVertexIdx(v)
            val iterator = graph.adjIterator(v)
            degreeList.add(vIdx, 0)
            while (iterator.hasNext()) {
                iterator.next()
                degreeList[vIdx]++
            }
        }
    }

    operator fun get(vertex: V): Int {
        val vIdx = graph.getVertexIdx(vertex)
        if (vIdx == -1) throw IllegalArgumentException("There's no such vertex: $vertex in the graph!")
        return degreeList[vIdx]
    }
}