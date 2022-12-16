package geeks_for_geeks.algorithms.graphs

object KruskalsMSTFinder {

    /**
     * Given an undirected weighted connected graph, finds the weight of
     * the Minimum Spanning Tree of the graph.
     * Time Complexity O(Elog(E))
     */
    fun <V : Comparable<V>> findMST(graph: Graph<V>): Double {
        // step 1. Sort the edges of the graph by ascending edge weight
        val sortedEdges = graph.getEdges().sortedBy { it.weight }
        val unionFind = UnionFind(graph.getVertexCount())
        var result = 0.0
        for (edge in sortedEdges) {
            val fromIdx = graph.getVertexIdx(edge.from)
            val toIdx = graph.getVertexIdx(edge.to)
            // if the vertices are already in the same group,
            // then skip them to avoid creating a cycle.
            if (unionFind.connected(fromIdx, toIdx)) continue

            // include this edge
            unionFind.unify(fromIdx, toIdx)
            result += edge.weight
            // Optimization to stop early if we found an MST
            // that includes all the nodes. In the end the component sizes
            // of all vertices must be equal to the number of vertices.
            if (unionFind.componentSize(0) == graph.getVertexCount()) {
                break
            }
        }
        if (unionFind.componentSize(0) != graph.getVertexCount()) {
            throw IllegalStateException("The graph: $graph doesn't have an MST! The graph is possibly" +
                    "disconnected.")
        }
        return result
    }
}