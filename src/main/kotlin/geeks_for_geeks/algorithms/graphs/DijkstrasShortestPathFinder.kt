package geeks_for_geeks.algorithms.graphs

import java.util.PriorityQueue

data class ShortestPath<V : Comparable<V>>(
    val vertex: V,
    val distance: Double
)

object DijkstrasShortestPathFinder {
    /**
     * Dijkstra's algorithm doesn't work for graphs with negative weights.
     */
    fun <V : Comparable<V>> findShortestPathsForSource(source: V, graph: Graph<V>): List<ShortestPath<V>> {
        if (graph.isDirected()) {
            throw UnsupportedOperationException("Dijkstras algorithm works for undirected graphs!")
        }
        val distances = PriorityQueue<ShortestPath<V>>(Comparator.comparing { it.distance })
        for (v in graph.vertices()) {
            val distance = if (v == source) {
                0.0
            } else {
                Double.POSITIVE_INFINITY
            }
            distances.add(ShortestPath(v, distance))
        }
        val result = mutableListOf<ShortestPath<V>>()
        val finalized = BooleanArray(graph.getVertexCount()) { false }
        while (distances.isNotEmpty()) {
            val min = distances.poll()
            val minIdx = graph.getVertexIdx(min.vertex)
            finalized[minIdx] = true
            result.add(min)
            val adjacent = graph.getAdjacentFor(min.vertex)
            for (adj in adjacent) {
                val adjIdx = graph.getVertexIdx(adj)
                if (!finalized[adjIdx]) {
                    relax(adj, min, distances, graph)
                }
            }
        }
        return result
    }

    private fun <V : Comparable<V>> relax(
        vertex: V,
        source: ShortestPath<V>,
        // Ideally what we need is an indexed priority queue, which allows for
        // decreasing a priority of an element by its index in the queue.
        distances: PriorityQueue<ShortestPath<V>>,
        graph: Graph<V>
    ) {
        val sourceWeight = source.distance
        val vertexShortestPath = distances.find { it.vertex == vertex }!!
        val vertexWeight = vertexShortestPath.distance
        val sourceToVertexWeight = graph.getWeight(source.vertex, vertex)
        if (vertexWeight > sourceWeight + sourceToVertexWeight) {
            distances.remove(vertexShortestPath)
            distances.add(ShortestPath(vertex, sourceWeight + sourceToVertexWeight))
        }
    }
}