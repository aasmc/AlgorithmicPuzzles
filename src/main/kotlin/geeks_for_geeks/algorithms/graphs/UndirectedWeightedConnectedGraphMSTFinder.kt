package geeks_for_geeks.algorithms.graphs

import java.util.Comparator
import java.util.PriorityQueue

object UndirectedWeightedConnectedGraphMSTFinder {
    /**
     * Computes the weight of all edges included in the MST of the given
     * undirected, connected, weighted graph.
     *
     * Prim's Algorithm.
     *
     * @return weight of all edges in MST
     */
    fun <V : Comparable<V>> findMST(graph: Graph<V>): Double {
        if (graph.isDirected()) {
            throw UnsupportedOperationException("MST cannot be found in the directed graph using this method.")
        }
        // Stores weights of vertices that have not been added to the MST
        val weights = PriorityQueue<Pair<Double, V>>(Comparator.comparing { it.first })
        // initially we add first vertex, although we could choose a random one
        weights.add(Pair(0.0, graph.vertices().first()))

        // Stores indices of the graph vertices which are included in the MST
        // Initially, no vertex is included in the MST
        val mstSet = BooleanArray(graph.getVertexCount()) { false }

        var result = 0.0

        while (weights.isNotEmpty()) {
            // retrieve the minimum weight vertex that has not yet been processed
            val min = weights.poll()
            val v = min.second
            val vIdx = graph.getVertexIdx(v)
            // discard vertex that has been added to the MST
            if (mstSet[vIdx]) continue
            // mark it as added to the MST
            mstSet[vIdx] = true
            // calculate the result
            result += min.first

            val adj = graph.getAdjacentFor(v)
            // for every adjacent vertex
            for (u in adj) {
                val uIdx = graph.getVertexIdx(u)
                if (!mstSet[uIdx]) { // if it has not been added to the MST
                    // add its weight and itself to the queue of unprocessed vertices
                    weights.add(Pair(graph.getWeight(v, u), u))
                }
            }
        }
        return result
    }
}