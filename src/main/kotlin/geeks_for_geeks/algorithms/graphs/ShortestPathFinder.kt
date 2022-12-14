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
     *
     */
    fun <V : Comparable<V>> findShortestPathsInDAG(graph: Graph<V>, source: V): DoubleArray {
        val topoSort = DAGraphTopologicalSorter.sortTopologicallyBFS(graph)
        val dist = DoubleArray(graph.getVertexCount()) { Double.POSITIVE_INFINITY }
        dist[graph.getVertexIdx(source)] = 0.0
        for (u in topoSort) {
            val uIdx = graph.getVertexIdx(u)
            for (v in graph.getAdjacentFor(u)) {
                val vIdx = graph.getVertexIdx(v)
                if (dist[vIdx] > dist[uIdx] + graph.getWeight(u, v)) {
                    dist[vIdx] = dist[uIdx] + graph.getWeight(u, v)
                }
            }
        }
        return dist
    }

    fun <V : Comparable<V>> bellmanFordSSSP(graph: Graph<V>, source: V): List<ShortestPath<V>> {
        val distances = mutableListOf<ShortestPath<V>>()
        for (v in graph.vertices()) {
            val distance = if (v == source) {
                0.0
            } else {
                Double.POSITIVE_INFINITY
            }
            distances.add(ShortestPath(v, distance))
        }

        distances.sortBy { graph.getVertexIdx(it.vertex) }

        val edges = graph.getEdges()
        for (count in 0 until graph.getVertexCount() - 1) {
            for (edge in edges) {
                val fromIdx = graph.getVertexIdx(edge.from)
                val toIdx = graph.getVertexIdx(edge.to)
                relax(distances, fromIdx, toIdx, edge.weight)
            }
        }
        // process negative weight cycles and mark all vertices either directly involved
        // in the cycle or reachable by a vertex in the cycle as having Double.NEGATIVE_INFINITY distance
        for (count in 0 until graph.getVertexCount() - 1) {
            for (edge in edges) {
                val fromIdx = graph.getVertexIdx(edge.from)
                val toIdx = graph.getVertexIdx(edge.to)
                val to = distances[toIdx]
                if (to.distance > distances[fromIdx].distance + edge.weight) {
                    distances[toIdx] = ShortestPath(to.vertex, Double.NEGATIVE_INFINITY)
                }
            }
        }
        return distances.toList()
    }

    private fun <V : Comparable<V>> relax(
        distances: MutableList<ShortestPath<V>>,
        fromIdx: Int,
        toIdx: Int,
        weight: Double
    ) {
        val to = distances[toIdx]
        val from = distances[fromIdx]
        if (to.distance > from.distance + weight) {
            distances[toIdx] = ShortestPath(to.vertex, from.distance + weight)
        }
    }
}

















