package geeks_for_geeks.algorithms.graphs

object ShortestPathFinder {

    /**
     * Algorithm:
     * 1. Find topological sort of the graph.
     * 2. Initialize an array [dist] of distances for every vertex with Double.POSITIVE_INFINITY
     * 3. Set distance for [source] = 0.0
     * 4. For every vertex U in topological sort:
     *      - for every adjacent V of U:
     *          relax distance of V:
     *          if dist[V] > dist[U] + weight[U, V]
     *              dist[V] = dist[U] + weight[U, V]
     */
    fun <V : Comparable<V>> findShortestPathsInDAG(graph: Graph<V>, source: V): DoubleArray {
        val topoSort = DAGraphTopologicalSorter.sortTopologicallyBFS(graph)
        val dist = DoubleArray(graph.getVertexCount()) { Double.POSITIVE_INFINITY }
        dist[graph.getVertexIdx(source)] = 0.0
        for (u in topoSort) {
            for (v in graph.getAdjacentFor(u)) {
                val vIdx = graph.getVertexIdx(v)
                val uIdx = graph.getVertexIdx(u)
                if (dist[vIdx] > dist[uIdx] + graph.getWeight(u, v)) {
                    dist[vIdx] = dist[uIdx] + graph.getWeight(u, v)
                }
            }
        }
        return dist
    }
}