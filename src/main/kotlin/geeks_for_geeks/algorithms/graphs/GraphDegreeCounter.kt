package geeks_for_geeks.algorithms.graphs

class GraphDegreeCounter<V: Comparable<V>>(
    private val graph: Graph<V>
) {
    private val outDegreeList: MutableList<Int> = ArrayList(graph.getVertexCount())
    private val inDegreeMap: MutableMap<V, Int> = hashMapOf()
    init {
        for (v in graph.vertices()) {
            outDegreeList.add(0)
            inDegreeMap[v] = 0
        }
        for (v in graph.vertices()) {
            val vIdx = graph.getVertexIdx(v)
            val iterator = graph.adjIterator(v)
            while (iterator.hasNext()) {
                val adj = iterator.next()
                inDegreeMap.merge(adj, 1, Int::plus)
                outDegreeList[vIdx]++
            }
        }
    }

    fun getOutDegree(vertex: V): Int {
        val vIdx = graph.getVertexIdx(vertex)
        if (vIdx == -1) throw IllegalArgumentException("There's no such vertex: $vertex in the graph!")
        return outDegreeList[vIdx]
    }

    fun getInDegree(vertex: V): Int {
        return inDegreeMap[vertex] ?: throw IllegalArgumentException("The vertex: $vertex doesn't belong to graph: ${graph.visualizeGraph()}")
    }

    fun getInDegreeMap(): Map<V, Int> {
        return inDegreeMap
    }
}