package geeks_for_geeks.algorithms.graphs

import kotlin.math.min

data class Bridge<V : Comparable<V>>(
    val from: V,
    val to: V
)

object BridgesFinder {

    fun <V : Comparable<V>> findBridges(graph: Graph<V>): List<Bridge<V>> {
        var discoveryTime = 0
        val visited = BooleanArray(graph.getVertexCount()) { false }
        val discoveryTimes = IntArray(graph.getVertexCount()) { 0 }
        val lowValues = IntArray(graph.getVertexCount()) { 0 }
        val parents = MutableList<V?>(graph.getVertexCount()) { null }
        val result = mutableListOf<Bridge<V>>()
        fun <V : Comparable<V>> DFSHelper(
            source: V,
            graph: Graph<V>,
            visited: BooleanArray,
            discoveryTimes: IntArray,
            lowValues: IntArray,
            parents: MutableList<V?>,
            result: MutableList<Bridge<V>>
        ) {
            val sourceIdx = graph.getVertexIdx(source)
            visited[sourceIdx] = true
            ++discoveryTime
            discoveryTimes[sourceIdx] = discoveryTime
            lowValues[sourceIdx] = discoveryTime

            for (adj in graph.getAdjacentFor(source)) {
                val adjIdx = graph.getVertexIdx(adj)
                if (!visited[adjIdx]) { // visit the vertex for the first time
                    parents[adjIdx] = source
                    DFSHelper(adj, graph, visited, discoveryTimes, lowValues, parents, result)

                    lowValues[sourceIdx] = minOf(lowValues[sourceIdx], lowValues[adjIdx])
                    // the essence of the algorithm! If the lowest discovery time reachable from
                    // current adjacent vertex is greater than the discovery time of its parent
                    // then the edge from parent to this adjacent vertex is a bridge
                    if (lowValues[adjIdx] > discoveryTimes[sourceIdx]) {
                        result.add(Bridge(source, adj))
                    }
                } else if (adj != parents[sourceIdx]) {
                    lowValues[sourceIdx] = min(lowValues[sourceIdx], discoveryTimes[adjIdx])
                }
            }
        }

        for (v in graph.vertices()) {
            val vIdx = graph.getVertexIdx(v)
            if (!visited[vIdx]) {
                DFSHelper(v, graph, visited, discoveryTimes, lowValues, parents, result)
            }
        }
        return result
    }

}