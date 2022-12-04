package geeks_for_geeks.algorithms.graphs

import java.util.PriorityQueue

object MSTFinder {
    /**
     * Computes the weight of all edges included in the MST of the given
     * undirected, connected, weighted graph.
     */
    fun <V : Comparable<V>> findMSTForUndirectedWeightedConnectedGraph(graph: Graph<V>): Double {
        if (graph.isDirected()) {
            throw UnsupportedOperationException("MST cannot be found in the directed graph using this method.")
        }
        // Stores weights of the edges between vertices which are not in MST.
        val keys = PriorityQueue<Double>()
        // initially there's a zero weight
        keys.add(0.0)
        // Stores indices of the graph vertices which are included in the MST
        // Initially, no vertex is included in the MST
        val mstSet = BooleanArray(graph.getVertexCount()) { false }

        var result = 0.0
        // traverse all the vertices of the graph
        for (v in graph.vertices()) { // O(V)
            // get the minimum weight of the edges, which are not in MST
            val min = keys.poll() // O(logE)
            result += min

            // mark this vertex as in the MST
            val vIdx = graph.getVertexIdx(v)
            mstSet[vIdx] = true

            val adj = graph.getAdjacentFor(v)
            for(u in adj) {
                val uIdx = graph.getVertexIdx(u)
                if (!mstSet[uIdx]) {
                    keys.add(graph.getWeight(v, u)) // O(logE)
                }
            }
        }
        return result
    }
}